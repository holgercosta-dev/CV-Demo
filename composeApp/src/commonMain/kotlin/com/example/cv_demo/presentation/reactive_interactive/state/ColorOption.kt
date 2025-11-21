package com.example.cv_demo.presentation.reactive_interactive.state

enum class ColorOption(private val hex: Long, val value: String) {
    FF828282(0xFF828282, "828282"),
    FF243452(0xFF243452, "243452"),
    FFF2F2F2(0xFFF2F2F2, "F2F2F2"),
    FF181819(0xFF181819, "181819");

    fun getARGB(): Long {
        return hex
    }

    companion object {
        fun from(value: String): ColorOption? {
            return ColorOption.entries.find { it.value == value }
        }
    }
}