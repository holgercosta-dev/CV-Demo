package com.example.cv_demo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cv_demo.presentation.home.HomeScreen
import com.example.cv_demo.presentation.reactive_interactive.ProductDetailsScreen
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    onNavHostReady: suspend (NavController) -> Unit = {},
) {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.Home
        ) {
            composable<Route.Home> {
                HomeScreen(navigateTo = { navController.navigate(it) })
            }
            composable<Route.ProductDetailsScreen> {
                ProductDetailsScreen()
            }
        }
        LaunchedEffect(navController) {
            onNavHostReady(navController)
        }
    }
}

@Serializable
sealed interface Route {
    @Serializable
    data object Home : Route
    @Serializable
    data object ProductDetailsScreen : Route

    companion object {
        fun getDestinations() = listOf(
            ProductDetailsScreen
        )
    }
}
