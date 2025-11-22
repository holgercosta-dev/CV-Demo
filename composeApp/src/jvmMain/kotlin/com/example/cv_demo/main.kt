package com.example.cv_demo

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.example.cv_demo.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "CVDemo",
        resizable = true,
        state = WindowState(width = 1200.dp, height = 850.dp),
    ) {
        App()
    }
}