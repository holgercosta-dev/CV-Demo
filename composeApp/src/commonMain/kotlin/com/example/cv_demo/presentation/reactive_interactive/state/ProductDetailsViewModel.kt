package com.example.cv_demo.presentation.reactive_interactive.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cv_demo.domain.use_case.product.GetProductDetailsType
import com.example.cv_demo.presentation.core.extension.send
import com.example.cv_demo.presentation.core.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn


class ProductDetailsViewModel(
    private val getProductDetails: GetProductDetailsType,
) : ViewModel() {
    private var selectedProductInnerState = SelectedProductInnerState()
    private var summaryState = SummaryState()

    private val userInteractionEvent = MutableSharedFlow<Unit>()
    private val productDetailsFlow = channelFlow<UiState<ProductUiDetails>> {
        val productDetails = getProductDetails()
        val mappedData = productDetails.mapToUi()
        //set default values for inner state
        selectedProductInnerState = selectedProductInnerState.copy(
            selectedFinish = mappedData.defaultFinish,
            selectedColor = mappedData.defaultColor,
            selectedStorage = mappedData.defaultStorage,
            selectedVariant = VariantOption.from(mappedData.defaultVariant?.name.orEmpty())
        )
        //initial values for summary
        summaryState = summaryState.copy(
//            shippingCosts = TODO(),
//            subTotal = TODO(),
//            total = TODO(),
//            description = TODO()
        )
        send(UiState.Success(mappedData))
    }
        .onStart { emit(UiState.Loading) }
        .shareIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            replay = 1,
        )

    private val onInteractionEventFlow: Flow<Unit> = userInteractionEvent.map {
        //At this point we know that the product details have been loaded
        val productDetailsSuccessFlow =
            productDetailsFlow.filterIsInstance<UiState.Success<ProductUiDetails>>()
        productDetailsSuccessFlow.collect { success ->
            val selectedProduct = selectedProductInnerState
            val summary = summaryState
            val productDetails = success.data

            if (selectedProduct.hasVariantChanged()) {
                //reset selected values to first option in list and update variant only
                selectedProductInnerState = selectedProductInnerState.copy()
            }
            //Get pricing info from api call
            summaryState = summaryState.copy()
        }
    }.onStart { emit(Unit) }

    val uiState: StateFlow<ProductDetailsState> = combine(
        productDetailsFlow,
        onInteractionEventFlow,
    ) { productDetailsState, _ ->
        ProductDetailsState(
            productDetails = productDetailsState,
            selectedProduct = selectedProductInnerState,
            summary = summaryState,
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
            is OnInteractionEvent.ChooseVariant -> selectedProductInnerState.copy(
                lastSelectedVariant = selectedProductInnerState.selectedVariant,
                selectedVariant = event.variant,
            )
        }
        userInteractionEvent.send(Unit)
    }

    fun addToCart() {
        println("Added to cart: $selectedProductInnerState")
    }
}