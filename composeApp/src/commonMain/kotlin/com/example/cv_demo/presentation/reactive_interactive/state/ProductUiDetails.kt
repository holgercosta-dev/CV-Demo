package com.example.cv_demo.presentation.reactive_interactive.state

import com.example.cv_demo.domain.model.product.Price
import com.example.cv_demo.domain.model.product.ProductDetails
import com.example.cv_demo.domain.model.product.ProductVariant

data class ProductUiDetails(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val productVariants: List<ProductUiVariant>
)

data class ProductUiVariant(
    val id: String,
    val name: String,
    val price: List<Price>,
    val finishOption: List<FinishOption>,
    val colorOptions: List<ColorOption>,
    val storageOptions: List<StorageOption>,
)

fun ProductVariant.mapToUi(): ProductUiVariant {
    return ProductUiVariant(
        id = this.id,
        name = this.name,
        price = this.price,
        finishOption = this.finishOption.items.mapNotNull {
            FinishOption.from(it)
        },
        colorOptions = this.colorOptions.items.mapNotNull {
            ColorOption.from(it)
        },
        storageOptions = this.storageOptions.items.mapNotNull {
            StorageOption.from(it)
        },
    )
}

fun ProductDetails.mapToUi(): ProductUiDetails {
    return ProductUiDetails(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl,
        productVariants = this.productVariants.map { it.mapToUi() }
    )
}
