package com.example.cv_demo.data.repository

import com.example.cv_demo.data.remote.api.product.ProductApi
import com.example.cv_demo.data.remote.dto.product.toDomain
import com.example.cv_demo.domain.model.product.ProductDetails
import com.example.cv_demo.domain.repository.product.ProductDetailsRepository

class ProductDetailsRepositoryImpl(
    private val productApi: ProductApi,
) : ProductDetailsRepository {
    override suspend fun getProductDetails(): ProductDetails {
        return productApi.getProductDetails().toDomain()
    }
}