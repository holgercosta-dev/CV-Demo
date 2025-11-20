package com.example.cv_demo.presentation.reactive_interactive.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cv_demo.domain.model.product.ProductDetails
import com.example.cv_demo.domain.use_case.product.GetProductDetailsType
import com.example.cv_demo.presentation.core.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


data class ProductDetailsState(
    val productDetails: UiState<ProductDetails> = UiState.Idle,
    val selectedProduct: SelectedProductInnerState? = null,
)

data class SelectedProductInnerState(
    val selectedFinish: String? = null,
    val selectedColor: String? = null,
    val selectedStorage: String? = null,
    val selectedVariant: String? = null,
)

sealed interface OnInteractionEvent {
    data object ChooseFinish : OnInteractionEvent
    data object ChooseColor : OnInteractionEvent
    data object ChooseStorage : OnInteractionEvent
    data object ChooseVariant : OnInteractionEvent
}

class ProductDetailsViewModel(
    private val getProductDetails: GetProductDetailsType,
) : ViewModel() {
    private val productDetailsFlow = channelFlow<UiState<ProductDetails>> {
        val productDetails = getProductDetails()
        send(UiState.Success(productDetails))
    }.onStart { emit(UiState.Loading) }

    private val selectedProductStateFlow: MutableStateFlow<SelectedProductInnerState?> = MutableStateFlow(null)

    val uiState: StateFlow<ProductDetailsState> = combine(
        productDetailsFlow,
        selectedProductStateFlow,
    ) { productDetailsState, selectedProduct ->
        ProductDetailsState(
            productDetails = productDetailsState,
            selectedProduct = selectedProduct,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProductDetailsState(),
    )

    fun onInteractionEvent(event: OnInteractionEvent) {
        when (event) {
            OnInteractionEvent.ChooseColor -> selectedProductStateFlow.update {
                it?.copy(selectedColor = TODO())
            }
            OnInteractionEvent.ChooseFinish -> selectedProductStateFlow.update {
                it?.copy(selectedFinish = TODO())
            }
            OnInteractionEvent.ChooseStorage -> selectedProductStateFlow.update {
                it?.copy(selectedStorage = TODO())
            }
            OnInteractionEvent.ChooseVariant -> selectedProductStateFlow.update {
                it?.copy(selectedVariant = TODO())
            }
        }
    }

    fun addToCart() {
        println("Added to cart: ${selectedProductStateFlow.value}")
    }
}