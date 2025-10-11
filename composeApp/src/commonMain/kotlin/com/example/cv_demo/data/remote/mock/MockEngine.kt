package com.example.cv_demo.data.remote.mock

import io.ktor.client.engine.HttpClientEngineBase
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.util.InternalAPI

class MockEngine(
    override val config: HttpClientEngineConfig = HttpClientEngineConfig()
) : HttpClientEngineBase("Mock_Engine") {
    @InternalAPI
    override suspend fun execute(data: HttpRequestData): HttpResponseData {
        TODO("Not yet implemented")
    }
}