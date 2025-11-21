package com.example.cv_demo.presentation.reactive_interactive.state

import com.example.cv_demo.domain.model.product.Price
import com.example.cv_demo.domain.model.product.ProductDetails
import com.example.cv_demo.domain.model.product.ProductVariant

data class ProductUiDetails(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val productVariants: List<ProductUiVariant>,
    val defaultFinish: FinishOption?,
    val defaultColor: ColorOption?,
    val defaultStorage: StorageOption?,
    val defaultVariant: ProductUiVariant?,
    val colorOptions: List<ColorOption> = emptyList(),
    val finishOptions: List<FinishOption> = emptyList(),
    val storageOptions: List<StorageOption> = emptyList(),
)

data class ProductUiVariant(
    val id: String,
    val name: String,
    val price: List<Price>,
    val finishOption: List<FinishOption>,
    val colorOptions: List<ColorOption>,
    val storageOptions: List<StorageOption>,
    val variantOption: VariantOption?,
)

fun ProductVariant.mapToUi(): ProductUiVariant {
    return ProductUiVariant(
        id = this.id,
        name = this.name,
        price = this.price,
        variantOption = VariantOption.from(this.name),
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
    val mappedVariants = this.productVariants.map { it.mapToUi() }
    return ProductUiDetails(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl,
        productVariants = mappedVariants,
        defaultVariant = mappedVariants.firstOrNull(),
        defaultFinish = FinishOption.from(productVariants.firstOrNull()?.finishOption?.items?.firstOrNull().orEmpty()),
        defaultColor = ColorOption.from(productVariants.firstOrNull()?.colorOptions?.items?.firstOrNull().orEmpty()),
        defaultStorage = StorageOption.from(productVariants.firstOrNull()?.storageOptions?.items?.firstOrNull().orEmpty()),
    )
}
