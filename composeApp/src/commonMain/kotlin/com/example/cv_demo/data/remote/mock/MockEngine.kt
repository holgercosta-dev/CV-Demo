package com.example.cv_demo.data.remote.mock

import io.ktor.client.engine.HttpClientEngineBase
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.callContext
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.util.InternalAPI
import io.ktor.util.date.GMTDate
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.core.toByteArray
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MockEngine(
    override val config: HttpClientEngineConfig = HttpClientEngineConfig()
) : HttpClientEngineBase("Mock_Engine") {
    @InternalAPI
    override suspend fun execute(data: HttpRequestData): HttpResponseData {
        val route = data.url.encodedPath
        println(route)
        val jsonString = Json.encodeToString(SomeResponse(id = "123"))
        val byteArray = jsonString.toByteArray()
        val callContext = callContext()
        val responseData = withContext(callContext) {
            when(route) {
                else -> HttpResponseData(
                    statusCode = HttpStatusCode.OK,
                    requestTime = GMTDate(),
                    headers = headersOf(
                        HttpHeaders.ContentType to listOf(ContentType.Application.Json.toString()),
                        HttpHeaders.ContentLength to listOf(byteArray.size.toString())
                    ),
                    version = HttpProtocolVersion.HTTP_1_1,
                    body = ByteReadChannel(byteArray),
                    callContext = callContext,
                )

            }
        }

        return responseData
    }
}

@Serializable
data class SomeResponse(val id: String)