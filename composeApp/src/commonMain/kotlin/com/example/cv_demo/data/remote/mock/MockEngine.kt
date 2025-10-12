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

class MockEngine(
    override val config: HttpClientEngineConfig = HttpClientEngineConfig(),
    private val mockDataHandler: MockDataHandler,
) : HttpClientEngineBase("Mock_Engine") {
    @InternalAPI
    override suspend fun execute(data: HttpRequestData): HttpResponseData {
        val callContext = callContext()
        val route = Route.fromPath(data.url.encodedPath)
        val responseByteArray: ByteArray = when (route) {
            Route.GET_USER_PROFILE -> {
                mockDataHandler.getUserProfile
            }
            Route.GET_SHOPPING_CART -> {
                mockDataHandler.getShoppingCart
            }
            Route.POST_VALIDATE_CREDIT_CARD -> {
                mockDataHandler.postValidateCreditCard
            }
            Route.POST_PURCHASE_ORDER -> {
                mockDataHandler.postPurchaseOrder
            }
            Route.UNKNOWN -> {
                mockDataHandler.getUserProfile
            }
        }

        return HttpResponseData(
            statusCode = HttpStatusCode.OK,
            requestTime = GMTDate(),
            headers = headersOf(
                HttpHeaders.ContentType to listOf(ContentType.Application.Json.toString()),
                HttpHeaders.ContentLength to listOf(responseByteArray.size.toString())
            ),
            version = HttpProtocolVersion.HTTP_1_1,
            body = ByteReadChannel(responseByteArray),
            callContext = callContext,
        )
    }
}