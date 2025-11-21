package com.example.cv_demo.presentation.reactive_interactive.state

enum class VariantOption : OptionGroupVertical {
    STANDARD {
        override val label: String
            get() = "Standard"
        override val description: String?
            get() = "6.1-inch Super Retina XDR display"
    },
    PRO {
        override val label: String
            get() = "Pro"
        override val description: String?
            get() = "6.7-inch Super Retina XDR display with ProMotion"
    };

    companion object {
        fun from(value: String): VariantOption? {
            return VariantOption.entries.find { it.label == value }
        }
    }
}