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
    val selectedFinish: String? = null,
    val selectedColor: ColorOption? = null,
    val selectedStorage: String? = null,
    val selectedVariant: String? = null,
)

sealed interface OnInteractionEvent {
    data object ChooseFinish : OnInteractionEvent
    data class ChooseColor(val color: ColorOption) : OnInteractionEvent
    data object ChooseStorage : OnInteractionEvent
    data object ChooseVariant : OnInteractionEvent
}

class ProductDetailsViewModel(
    private val getProductDetails: GetProductDetailsType,
) : ViewModel() {

    private lateinit var selectedProductInnerState: SelectedProductInnerState

    private val userInteractionEvent = MutableSharedFlow<Unit>()
    private val productDetailsFlow = channelFlow<UiState<ProductUiDetails>> {
        val productDetails = getProductDetails()
        val mappedData = productDetails.mapToUi()
        //set default values for inner state
        selectedProductInnerState = SelectedProductInnerState(
            selectedFinish = TODO(),
            selectedColor = TODO(),
            selectedStorage = TODO(),
            selectedVariant = TODO()
        )
        send(UiState.Success(mappedData))
    }.onStart { emit(UiState.Loading) }

    val uiState: StateFlow<ProductDetailsState> = combine(
        productDetailsFlow,
        userInteractionEvent.onStart { emit(Unit) },
    ) { productDetailsState, _ ->
        ProductDetailsState(
            productDetails = productDetailsState,
            selectedProduct = selectedProductInnerState
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProductDetailsState(),
    )

    fun onInteractionEvent(event: OnInteractionEvent) {
        when (event) {
            is OnInteractionEvent.ChooseColor -> selectedProductInnerState.copy(selectedColor = event.color)
            OnInteractionEvent.ChooseFinish -> selectedProductInnerState.copy(selectedFinish = "")
            OnInteractionEvent.ChooseStorage -> selectedProductInnerState.copy(selectedStorage = "")
            OnInteractionEvent.ChooseVariant -> selectedProductInnerState.copy(selectedVariant = "")
        }
        userInteractionEvent.send(Unit)
    }

    fun addToCart() {
        println("Added to cart: $selectedProductInnerState")
    }
}