package com.example.cv_demo.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AppClientImpl(
    private val httpClient: HttpClient,
) : AppClient {

    override suspend fun get(path: String, queryParams: Map<String, Any?>): Result<HttpResponse> {
        return performRequest {
            httpClient.get(path) {
                contentType(ContentType.Application.Json)
                // Add all query parameters from the map
                queryParams.forEach { (key, value) ->
                    parameter(key, value)
                }
            }
        }
    }

    override suspend fun post(path: String, block: HttpRequestBuilder.() -> Unit): Result<HttpResponse> {
        return performRequest {
            httpClient.post(path, block)
        }
    }

    override suspend fun put(path: String, block: HttpRequestBuilder.() -> Unit): Result<HttpResponse> {
        return performRequest {
            httpClient.put(path, block)
        }
    }

    override suspend fun delete(path: String): Result<HttpResponse> {
        return performRequest {
            httpClient.delete(path) {
                contentType(ContentType.Application.Json)
            }
        }
    }

    /**
     * A helper function to wrap Ktor calls in a try-catch block,
     * returning a Result object for safe error handling.
     */
    private suspend fun performRequest(block: suspend () -> HttpResponse): Result<HttpResponse> {
        return try {
            Result.success(block())
        } catch (e: Exception) {
            // Catches network errors, serialization errors, etc.
            Result.failure(e)
        }
    }
}

/**
 * Sends a POST request to the specified [url].
 * Helper to bypass type erasure issue.
 *
 * @param url The URL to send the request to.
 * @param body The body of the request.
 * @return The [HttpResponse] from the server.
 */
suspend inline fun <reified T : Any> AppClient.post(path: String, body: T): Result<HttpResponse> {
    return post(path) {
        contentType(ContentType.Application.Json)
        setBody(body)
    }
}

/**
 * Sends a PUT request to the specified [url].
 * Helper to bypass type erasure issue.
 *
 * @param url The URL to send the request to.
 * @param body The body of the request.
 * @return The [HttpResponse] from the server.
 */
suspend inline fun <reified T : Any> AppClient.put(path: String, body: T): Result<HttpResponse> {
    return put(path) {
        contentType(ContentType.Application.Json)
        setBody(body)
    }
}