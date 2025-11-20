package com.example.cv_demo.domain.model.product


data class ProductDetails(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val productVariants: List<ProductVariant>,
)
