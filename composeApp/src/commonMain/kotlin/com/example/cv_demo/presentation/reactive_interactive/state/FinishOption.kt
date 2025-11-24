package com.example.cv_demo.presentation.reactive_interactive.state

enum class FinishOption(val label: String) {
    MATTE("Matte"),
    GLOSSY("Glossy");

    companion object {
        fun from(value: String): FinishOption? {
            return FinishOption.entries.find { it.name == value }
        }
    }
}