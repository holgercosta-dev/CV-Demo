package com.example.cv_demo.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.cv_demo.data.remote.mock.Route

@Composable
fun HomeScreen(navigateTo: (Route) -> Unit) {
    Column {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            val items = (1..100).map { it.toString() }
            items(items) {
                Text("Demo $it")
            }
        }
    }
}