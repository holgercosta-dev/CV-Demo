package com.example.cv_demo.domain.model.product

import com.example.cv_demo.data.remote.dto.product.ProductOptionDto

data class ProductDetails(
    val id: String,
    val name: String,
    val description: String,
    val price: List<Price>,
    val imageUrl: String,
    val finishOption: ProductOptionDto,
    val colorOptions: ProductOptionDto,
    val storageOptions: ProductOptionDto,
    val modelOptions: ProductOptionDto,
)
