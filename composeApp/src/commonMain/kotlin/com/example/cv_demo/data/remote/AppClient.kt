package com.example.cv_demo.data.remote

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse

/**
 * A low-level client interface for making HTTP requests.
 * Functions are suspending and return a Result, promoting safe concurrency and error handling.
 */
interface AppClient {
    /**
     * Performs a GET request.
     * @param path The endpoint path (e.g., "/users").
     * @param queryParams A map of query parameters to be appended to the URL.
     * @return A Result containing the HttpResponse on success or an Exception on failure.
     */
    suspend fun get(
        path: String,
        queryParams: Map<String, Any?> = emptyMap()
    ): Result<HttpResponse>

    /**
     * Performs a POST request.
     *
     * This function is used for creating a new resource on the server.
     *
     * Example usage:
     * ```kotlin
     * client.post("/users") {
     *     contentType(ContentType.Application.Json)
     *     setBody(User(name = "Jane Doe", email = "jane.doe@example.com"))
     * }
     * ```
     *
     * @param path The endpoint path (e.g., "/users").
     * @param block A lambda with an [HttpRequestBuilder] receiver for configuring the request,
     *              such as setting the body and headers.
     * @return A [Result] containing the [HttpResponse] on success or an [Exception] on failure.
     */
    suspend fun post(path: String, block: HttpRequestBuilder.() -> Unit): Result<HttpResponse>

    /**
     * Performs a PUT request.
     * @param path The endpoint path.
     * @param block A lambda with [HttpRequestBuilder] as its receiver to configure the request,
     *              such as setting the body and headers.
     * @return A [Result] containing the [HttpResponse] on success or an [Exception] on failure.
     */
    suspend fun put(path: String, block: HttpRequestBuilder.() -> Unit): Result<HttpResponse>

    /**
     * Performs a DELETE request.
     * @param path The endpoint path.
     * @return A Result containing the HttpResponse on success or an Exception on failure.
     */
    suspend fun delete(path: String): Result<HttpResponse>
}