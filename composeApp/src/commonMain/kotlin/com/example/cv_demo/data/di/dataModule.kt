package com.example.cv_demo.data.di

import com.example.cv_demo.data.remote.AppClient
import com.example.cv_demo.data.remote.AppClientImpl
import com.example.cv_demo.data.remote.mock.MockDataHandler
import com.example.cv_demo.data.remote.mock.MockEngine
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

val dataModule = module {

    factory { MockDataHandler() }
    factory<HttpClientEngine> { MockEngine(mockDataHandler = get()) }

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
        )
    }
}