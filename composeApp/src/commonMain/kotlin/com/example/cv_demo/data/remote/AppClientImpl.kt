package com.example.cv_demo.data.remote

import com.example.cv_demo.data.remote.mock.MockWebsocketSession
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.websocket.WebSocketSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AppClientImpl(
    private val httpClient: HttpClient,
) : AppClient {

    private lateinit var webSocketSession: WebSocketSession

    override fun makeRequest() {
        CoroutineScope(Dispatchers.Default).launch {
            webSocketSession = MockWebsocketSession()

            val response = async {
                httpClient.get(
                    builder = HttpRequestBuilder().apply {
                        url("https://example.com/some/path")
                    }
                )
            }
        }
    }
}