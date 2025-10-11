package com.example.cv_demo.data.remote

import io.ktor.client.HttpClient
import io.ktor.websocket.WebSocketSession

class AppClientImpl(
    private val httpClient: HttpClient,
    private val websocketSession: WebSocketSession,
) : AppClient {

    init {
        websocketSession.incoming
    }
}