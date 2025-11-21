package com.example.cv_demo.presentation.reactive_interactive.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cv_demo.domain.use_case.product.GetProductDetailsType
import com.example.cv_demo.presentation.core.extension.send
import com.example.cv_demo.presentation.core.state.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn


data class ProductDetailsState(
    val productDetails: UiState<ProductUiDetails> = UiState.Idle,
    val selectedProduct: SelectedProductInnerState? = null,
)

data class SelectedProductInnerState(
    val selectedFinish: FinishOption? = null,
    val selectedColor: ColorOption? = null,
    val selectedStorage: StorageOption? = null,
    val selectedVariantId: String? = null,
)

sealed interface OnInteractionEvent {
    data class ChooseFinish(val finish: FinishOption) : OnInteractionEvent
    data class ChooseColor(val color: ColorOption) : OnInteractionEvent
    data class ChooseStorage(val storage: StorageOption) : OnInteractionEvent
    data class ChooseVariant(val variant: ProductUiVariant) : OnInteractionEvent
}

class ProductDetailsViewModel(
    private val getProductDetails: GetProductDetailsType,
) : ViewModel() {

    private var selectedProductInnerState = SelectedProductInnerState()

    private val userInteractionEvent = MutableSharedFlow<Unit>()
    private val productDetailsFlow = channelFlow<UiState<ProductUiDetails>> {
        val productDetails = getProductDetails()
        val mappedData = productDetails.mapToUi()
        //set default values for inner state
        selectedProductInnerState = SelectedProductInnerState(
            selectedFinish = mappedData.defaultFinish,
            selectedColor = mappedData.defaultColor,
            selectedStorage = mappedData.defaultStorage,
            selectedVariantId = mappedData.defaultVariant?.id
        )
        send(UiState.Success(mappedData))
    }.onStart { emit(UiState.Loading) }

    val uiState: StateFlow<ProductDetailsState> = combine(
        productDetailsFlow,
        userInteractionEvent.onStart { emit(Unit) },
    ) { productDetailsState, _ ->
        ProductDetailsState(
            productDetails = productDetailsState,
            selectedProduct = selectedProductInnerState,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProductDetailsState(),
    )

    fun onInteractionEvent(event: OnInteractionEvent) {
        selectedProductInnerState = when (event) {
            is OnInteractionEvent.ChooseColor -> selectedProductInnerState.copy(selectedColor = event.color)
            is OnInteractionEvent.ChooseFinish -> selectedProductInnerState.copy(selectedFinish = event.finish)
            is OnInteractionEvent.ChooseStorage -> selectedProductInnerState.copy(selectedStorage = event.storage)
            is OnInteractionEvent.ChooseVariant -> selectedProductInnerState.copy(selectedVariantId = event.variant.id)
        }
        userInteractionEvent.send(Unit)
    }

    fun addToCart() {
        println("Added to cart: $selectedProductInnerState")
    }
}