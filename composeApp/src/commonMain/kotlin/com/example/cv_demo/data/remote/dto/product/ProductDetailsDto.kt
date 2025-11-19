package com.example.cv_demo.data.remote.dto.product

import com.example.cv_demo.domain.model.product.Price
import com.example.cv_demo.domain.model.product.ProductDetails
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailsDto(
    val id: String,
    val name: String,
    val description: String,
    val price: List<PriceDto>,
    val imageUrl: String,
    val finishOption: ProductOptionDto,
    val colorOptions: ProductOptionDto,
    val storageOptions: ProductOptionDto,
    val modelOptions: ProductOptionDto,
)

@Serializable
data class PriceDto(
    val value: Double,
    val currency: String,
)

fun PriceDto.toDomain(): Price {
    return Price(
        value = this.value,
        currency = this.currency
    )
}

@Serializable
data class ProductOptionDto(
    val label: String,
    val items: List<String>
)

fun ProductDetailsDto.toDomain(): ProductDetails {
    return ProductDetails(
        id = this.id,
        name = this.name,
        description = this.description,
        price = this.price.map { it.toDomain() },
        imageUrl = this.imageUrl,
        finishOption = this.finishOption,
        colorOptions = this.colorOptions,
        storageOptions = this.storageOptions,
        modelOptions = this.modelOptions,
    )
}