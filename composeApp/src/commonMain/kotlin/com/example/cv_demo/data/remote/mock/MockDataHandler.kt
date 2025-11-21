package com.example.cv_demo.data.remote.mock

import com.example.cv_demo.data.remote.dto.product.PriceDto
import com.example.cv_demo.data.remote.dto.product.ProductDetailsDto
import com.example.cv_demo.data.remote.dto.product.ProductOptionDto
import com.example.cv_demo.data.remote.dto.product.ProductVariantDto
import io.ktor.utils.io.core.toByteArray
import kotlinx.serialization.json.Json
import kotlin.uuid.ExperimentalUuidApi

class MockDataHandler(
    private val json: Json,
) {
    @OptIn(ExperimentalUuidApi::class)
    val getProductDetails: ByteArray
        get() = ProductDetailsDto(
            id = "prod_12345",
            name = "Premium Wireless Headphones",
            description = "Experience high-fidelity sound with our latest noise-cancelling technology. Perfect for travel and daily commutes.",
            imageUrl = "https://placehold.co/600x400/png",
            productVariants = listOf(
                ProductVariantDto(
                    name = "Standard",
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
                            currency = "AUD",
                        )
                    ),
                    finishOption = ProductOptionDto(
                        label = "Finish",
                        items = listOf(
                            "MATTE",
                            "GLOSSY"
                        )
                    ),
                    colorOptions = ProductOptionDto(
                        label = "Color",
                        items = listOf(
                            "828282",
                            "243452",
                            "F2F2F2",
                            "181819"
                        )
                    ),
                    storageOptions = ProductOptionDto(
                        label = "Storage",
                        items = listOf("128GB", "256GB", "512GB", "1TB")
                    ),
                ),
                ProductVariantDto(
                    name = "Pro",
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
                            currency = "AUD",
                        )
                    ),
                    finishOption = ProductOptionDto(
                        label = "Finish",
                        items = listOf(
                            "MATTE",
                            "GLOSSY"
                        )
                    ),
                    colorOptions = ProductOptionDto(
                        label = "Color",
                        items = listOf(
                            "828282",
                            "243452",
                            "F2F2F2",
                            "181819"
                        )
                    ),
                    storageOptions = ProductOptionDto(
                        label = "Storage",
                        items = listOf("128GB", "256GB", "512GB", "1TB")
                    ),
                ),
            )
        )
            .run { json.encodeToString(ProductDetailsDto.serializer(), this) }
            .toByteArray()
}