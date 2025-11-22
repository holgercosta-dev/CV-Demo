package com.example.cv_demo.presentation.reactive_interactive.state

data class SelectedProductInnerState(
    val selectedFinish: FinishOption? = null,
    val selectedColor: ColorOption? = null,
    val selectedStorage: StorageOption? = null,
    val selectedVariant: VariantOption? = null,
)