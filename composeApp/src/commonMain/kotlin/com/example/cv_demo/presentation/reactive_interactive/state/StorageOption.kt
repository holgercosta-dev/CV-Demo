package com.example.cv_demo.presentation.reactive_interactive.state

enum class StorageOption(val value: String) : OptionGroupVertical {
    GB_128("128GB") {
        override val label: String
            get() = "128 GB"
        override val description: String?
            get() = null
    },
    GB_256("256GB") {
        override val label: String
            get() = "256 GB"
        override val description: String?
            get() = null
    },
    GB_512("512GB") {
        override val label: String
            get() = "512 GB"
        override val description: String?
            get() = null
    },
    GB_1("1TB") {
        override val label: String
            get() = "1 TB"
        override val description: String?
            get() = null
    };
    companion object {
        fun from(value: String): StorageOption? {
            return StorageOption.entries.find { it.value == value }
        }
    }
}