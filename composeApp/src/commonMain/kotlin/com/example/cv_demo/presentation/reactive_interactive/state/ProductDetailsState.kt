package com.example.cv_demo.presentation.reactive_interactive.state

import com.example.cv_demo.presentation.core.state.UiState

data class ProductDetailsState(
    val productDetails: UiState<ProductUiDetails> = UiState.Idle,
    val selectedProduct: SelectedProductInnerState? = null,
    val summary: SummaryState = SummaryState(),
)