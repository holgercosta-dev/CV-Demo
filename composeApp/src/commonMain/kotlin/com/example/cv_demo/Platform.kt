package com.example.cv_demo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform