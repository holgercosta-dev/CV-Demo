package com.example.cv_demo.data.remote.dto.product

import com.example.cv_demo.domain.model.product.Price
import com.example.cv_demo.domain.model.product.ProductDetails
import com.example.cv_demo.domain.model.product.ProductOption
import com.example.cv_demo.domain.model.product.ProductVariant
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class ProductDetailsDto(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val productVariants: List<ProductVariantDto>,
)

@Serializable
data class ProductVariantDto @OptIn(ExperimentalUuidApi::class) constructor(
    val id: String = Uuid.random().toString(),
    val name: String,
    val price: List<PriceDto>,
    val finishOption: ProductOptionDto,
    val colorOptions: ProductOptionDto,
    val storageOptions: ProductOptionDto,
)

fun ProductVariantDto.toDomain(): ProductVariant {
    return ProductVariant(
        id = this.id,
        name = this.name,
        price = this.price.map { it.toDomain() },
        finishOption = this.finishOption.toDomain(),
        colorOptions = this.colorOptions.toDomain(),
        storageOptions = this.storageOptions.toDomain(),
    )
}

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

fun ProductOptionDto.toDomain(): ProductOption {
    return ProductOption(
        label = this.label,
        items = this.items,
    )
}

fun ProductDetailsDto.toDomain(): ProductDetails {
    return ProductDetails(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl,
        productVariants = this.productVariants.map { it.toDomain() }
    )
}