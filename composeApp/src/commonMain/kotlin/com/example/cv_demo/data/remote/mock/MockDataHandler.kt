package com.example.cv_demo.data.remote.mock

import com.example.cv_demo.data.remote.dto.product.PriceDto
import com.example.cv_demo.data.remote.dto.product.ProductDetailsDto
import com.example.cv_demo.data.remote.dto.product.ProductOptionDto
import io.ktor.utils.io.core.toByteArray
import kotlinx.serialization.json.Json

class MockDataHandler(
    private val json: Json,
) {
    val getProductDetails: ByteArray
        get() = ProductDetailsDto(
            id = "prod_12345",
            name = "Titanium Pro X1",
            description = "Experience the pinnacle of mobile technology with the Titanium Pro X1. Featuring a revolutionary 6.8-inch Super Retina XDR display, the A18 Bionic chip for lightning-fast performance, and a pro-grade triple camera system that captures stunning detail in any light. Encased in aerospace-grade titanium, it's lighter, stronger, and more durable than ever before.",
            price = listOf(
                PriceDto(
                    value = 1099.0,
                    currency = "USD"
                ),
                PriceDto(
                    value = 1199.0,
                    currency = "EUR"
                ),
                PriceDto(
                    value = 1000.0,
                    currency = "GBP"
                ),
                PriceDto(
                    value = 999.0,
                    currency = "CHF"
                ),
                PriceDto(
                    value = 1999.0,
                    currency = "AUD"
                ),
            ),
            imageUrl = "https://placehold.co/600x800/png", // Placeholder image
            finishOption = ProductOptionDto(
                label = "Finish",
                items = listOf(
                    "Natural Titanium",
                    "Blue Titanium",
                    "White Titanium",
                    "Black Titanium"
                )
            ),
            colorOptions = ProductOptionDto(
                label = "Color",
                items = listOf("#828282", "#243452", "#F2F2F2", "#181819") // Hex codes for colors
            ),
            storageOptions = ProductOptionDto(
                label = "Storage",
                items = listOf("128GB", "256GB", "512GB", "1TB")
            ),
            modelOptions = ProductOptionDto(
                label = "Model",
                items = listOf("Titanium Pro X1", "Titanium Pro X1 Max")
            )
        )
            .run { json.encodeToString(ProductDetailsDto.serializer(), this) }
            .toByteArray()

}