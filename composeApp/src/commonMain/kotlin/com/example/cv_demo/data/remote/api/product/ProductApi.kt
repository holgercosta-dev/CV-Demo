package com.example.cv_demo.data.remote.api.product

import com.example.cv_demo.data.remote.client.AppClient
import com.example.cv_demo.data.remote.dto.product.ProductDetailsDto
import com.example.cv_demo.data.remote.mock.Route
import io.ktor.client.call.body

interface ProductApi {
    suspend fun getProductDetails(): ProductDetailsDto
}

class ProductApiImpl(private val client: AppClient) : ProductApi {
    override suspend fun getProductDetails(): ProductDetailsDto {
        return client.get(Route.GET_PRODUCT_DETAILS.path)
            .body()
    }
}