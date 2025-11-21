package com.example.cv_demo.presentation.reactive_interactive.state

enum class ColorOption(val value: String) {
    FF828282("828282"),
    FF243452("243452"),
    FFF2F2F2("F2F2F2"),
    FF181819("181819");

    fun getARGB(): Long {
        return "oxFF$value".toLong()
    }

    companion object {
        fun from(value: String): ColorOption? {
            return ColorOption.entries.find { it.name == value }
        }
    }
}