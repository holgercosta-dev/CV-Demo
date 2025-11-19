package com.example.cv_demo.data.di

import com.example.cv_demo.data.remote.api.product.ProductApi
import com.example.cv_demo.data.remote.api.product.ProductApiImpl
import com.example.cv_demo.data.remote.client.AppClient
import com.example.cv_demo.data.remote.client.AppClientImpl
import com.example.cv_demo.data.remote.mock.MockDataHandler
import com.example.cv_demo.data.remote.mock.MockEngine
import com.example.cv_demo.data.repository.ProductDetailsRepositoryImpl
import com.example.cv_demo.domain.repository.product.ProductDetailsRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {

    factory { Json { ignoreUnknownKeys = true; prettyPrint = true; isLenient = true } }
    factoryOf(::MockDataHandler)
    factoryOf(::MockEngine) { bind<HttpClientEngine>() }

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
            baseUrl = "https://example.com"
        )
    }

    factoryOf(::ProductDetailsRepositoryImpl) { bind<ProductDetailsRepository>() }
    factoryOf(::ProductApiImpl) { bind<ProductApi>() }
}