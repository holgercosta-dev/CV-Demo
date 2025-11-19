package com.example.cv_demo.data.remote.client

import io.ktor.client.statement.HttpResponse

interface AppClient {
    suspend fun get(path: String): HttpResponse
}