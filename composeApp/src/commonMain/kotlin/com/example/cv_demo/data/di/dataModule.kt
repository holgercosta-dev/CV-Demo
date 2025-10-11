package com.example.cv_demo.data.di

import com.example.cv_demo.data.remote.AppClient
import com.example.cv_demo.data.remote.AppClientImpl
import com.example.cv_demo.data.remote.mock.MockEngine
import com.example.cv_demo.data.remote.mock.MockWebsocketSession
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineBase
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.websocket.DefaultWebSocketSession
import org.koin.dsl.module

val dataModule = module {

    factory<HttpClientEngine> { MockEngine() }
    factory<DefaultWebSocketSession> { MockWebsocketSession() }

    single {
        HttpClient(get<HttpClientEngine>()) {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    single<AppClient> {
        AppClientImpl(
            httpClient = get(),
            websocketSession = get(),
        )
    }
}