package com.example.cv_demo.presentation.reactive_interactive.state

interface MappedSummaryState {
    val shippingCosts: String?
    val subTotal: String
    val total: String
    val description: String
}

class SummaryMapper {
    fun getMappedSummaryState(
        shippingCosts: String?,
        subTotal: Double?,
        selectedProductInnerState: SelectedProductInnerState,
    ) = object : MappedSummaryState {
        override val shippingCosts: String?
            get() = shippingCosts?.let { "$$it" }
        override val subTotal: String
            get() = subTotal?.let { "$$it" }.orEmpty()
        override val total: String
            get() = subTotal?.let {
                getCalculatedTotal(it)
            }.orEmpty()
        override val description: String
            get() = getDescription(selectedProductInnerState)
    }
}

private fun getCalculatedTotal(subTotal: Double): String {
    val simulatedTax = 0.12
    val calculatedTotal = (subTotal * simulatedTax) + subTotal
    return "$$calculatedTotal"
}

private fun getDescription(selectedProductInnerState: SelectedProductInnerState): String {
    val variantLabel = selectedProductInnerState.selectedVariant?.label.orEmpty()
    val finishLabel = selectedProductInnerState.selectedFinish?.label.orEmpty()
    val storageLabel = selectedProductInnerState.selectedStorage?.label.orEmpty()
    return "$variantLabel, $finishLabel, $storageLabel"
}