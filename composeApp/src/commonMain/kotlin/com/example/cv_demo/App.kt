package com.example.cv_demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.cv_demo.presentation.reactive_interactive.ProductDetailsScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val backStack = mutableStateListOf<Route>(Route.Home)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Route.Home> {
                //HomeScreen()
            }
            entry<Route.ProductDetailsScreen> {
                ProductDetailsScreen()
            }
        },
    )
}

sealed interface Route : NavKey {
    data object Home : Route
    data object ProductDetailsScreen : Route
}
