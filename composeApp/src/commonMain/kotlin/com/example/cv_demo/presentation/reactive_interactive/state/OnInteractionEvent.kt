package com.example.cv_demo.presentation.reactive_interactive.state

sealed interface OnInteractionEvent {
    data class ChooseFinish(val finish: FinishOption) : OnInteractionEvent
    data class ChooseColor(val color: ColorOption) : OnInteractionEvent
    data class ChooseStorage(val storage: StorageOption) : OnInteractionEvent
    data class ChooseVariant(val variant: VariantOption) : OnInteractionEvent
}