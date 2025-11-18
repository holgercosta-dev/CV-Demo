package com.example.cv_demo.presentation.reactive_interactive.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class ProductDetailsViewModel : ViewModel() {
    val uiState: StateFlow<Any> = combine(
        flowOf(Any()),
        flowOf(Any())
    ) { _, _ ->
        Any()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Any(),
    )
}