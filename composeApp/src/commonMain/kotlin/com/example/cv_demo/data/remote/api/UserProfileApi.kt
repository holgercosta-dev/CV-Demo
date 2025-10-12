package com.example.cv_demo.data.remote.api

import com.example.cv_demo.data.remote.AppClient

class UserProfileApi(
    private val client: AppClient,
) {
    suspend fun getUserProfile(): Any {
        return client.get("user/profile")

    }
}