package com.example.cv_demo.presentation.reactive_interactive.state

enum class FinishOption {
    MATTE,
    GLOSSY;

    companion object {
        fun from(value: String): FinishOption? {
            return FinishOption.entries.find { it.name == value }
        }
    }
}