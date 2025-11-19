package com.example.cv_demo.domain.repository.product

import com.example.cv_demo.domain.model.product.ProductDetails

interface ProductDetailsRepository {
    suspend fun getProductDetails(): ProductDetails
}