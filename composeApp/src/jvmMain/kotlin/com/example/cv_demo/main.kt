package com.example.cv_demo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.cv_demo.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "CVDemo",
    ) {
        App()
    }
}