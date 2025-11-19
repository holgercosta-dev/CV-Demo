package com.example.cv_demo.data.remote.client

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse

class AppClientImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String,
) : AppClient {
    override suspend fun get(path: String): HttpResponse {
        return httpClient.get(
            builder = HttpRequestBuilder().apply {
                url("$baseUrl$path")
            }
        )
    }
}