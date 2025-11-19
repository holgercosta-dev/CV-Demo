package com.example.cv_demo.domain.use_case.product

import com.example.cv_demo.domain.repository.product.ProductDetailsRepository

typealias GetProductDetailsType = suspend () -> ProductDetails

class GetProductDetails(
    private val repository: ProductDetailsRepository,
) : GetProductDetailsType {
    override suspend fun invoke(): ProductDetails {
        return repository.getProductDetails()
    }
}