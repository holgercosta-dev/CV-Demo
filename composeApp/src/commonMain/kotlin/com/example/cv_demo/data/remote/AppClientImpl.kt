package com.example.cv_demo.data.remote

import com.example.cv_demo.data.remote.mock.SomeResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.websocket.DefaultWebSocketSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AppClientImpl(
    private val httpClient: HttpClient,
    private val websocketSession: DefaultWebSocketSession,
) : AppClient {


    override fun makeRequest() {
        CoroutineScope(Dispatchers.Default).launch {
            val response = async {
                httpClient.get(
                    builder = HttpRequestBuilder().apply {
                        url("https://example.com/some/path")
                    }
                )
            }

            val body = response.await().body<SomeResponse>()
            body.id
        }
    }
}