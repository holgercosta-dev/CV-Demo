package com.example.cv_demo.data.di

import com.example.cv_demo.data.remote.AppClient
import com.example.cv_demo.data.remote.AppClientImpl
import com.example.cv_demo.data.remote.mock.MockEngine
import com.example.cv_demo.data.remote.mock.MockWebsocketSession
import io.ktor.client.HttpClient
import org.koin.dsl.module

val dataModule = module {

    factory { MockEngine() }
    factory { MockWebsocketSession() }

    single {
        HttpClient(get<MockEngine>())
    }

    single<AppClient> {
        AppClientImpl(
            httpClient = get(),
            websocketSession = get(),
        )
    }
}