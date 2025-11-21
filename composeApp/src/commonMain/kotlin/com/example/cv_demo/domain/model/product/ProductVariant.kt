package com.example.cv_demo.domain.model.product

data class ProductVariant(
    val id: String,
    val name: String,
    val price: List<Price>,
    val finishOption: ProductOption,
    val colorOptions: ProductOption,
    val storageOptions: ProductOption,
)
