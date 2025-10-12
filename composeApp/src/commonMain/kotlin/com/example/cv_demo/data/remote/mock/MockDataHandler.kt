package com.example.cv_demo.data.remote.mock

import io.ktor.utils.io.core.toByteArray
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MockDataHandler(
    private val json: Json = Json { ignoreUnknownKeys = true },
) {

    val getUserProfile: ByteArray
        get() = UserProfile(
            id = "id",
            name = "name",
            email = "email@email.com",
        ).encodeToByteArrayWithDefault()

    val getShoppingCart: ByteArray
        get() = TODO()

    val postValidateCreditCard: ByteArray
        get() = TODO()

    val postPurchaseOrder: ByteArray
        get() = TODO()

    private inline fun <reified T> T.encodeToByteArrayWithDefault(): ByteArray {
        return runCatching {
            json.encodeToString(this)
                .toByteArray()
        }.getOrDefault(defaultValue = byteArrayOf())
    }
}

@Serializable
data class UserProfile(val id: String, val name: String, val email: String)