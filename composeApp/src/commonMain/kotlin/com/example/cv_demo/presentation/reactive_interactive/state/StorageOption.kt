package com.example.cv_demo.presentation.reactive_interactive.state

enum class StorageOption(val value: String) {
    GB_128("128GB"),
    GB_256("256GB"),
    GB_512("512GB"),
    GB_1("1TB");
    companion object {
        fun from(value: String): StorageOption? {
            return StorageOption.entries.find { it.value == value }
        }
    }
}