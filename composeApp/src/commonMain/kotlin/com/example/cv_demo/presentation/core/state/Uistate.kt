package com.example.cv_demo.presentation.core.state

/**
 * A generic sealed class that represents the different states of a UI screen.
 * It is generic, allowing it to hold any type of data in its Success state.
 *
 * @param T The type of data to be held in the Success state.
 */
sealed class UiState<out T> {
    /**
     * Represents the initial, default state of the UI before any operation has started.
     */
    data object Idle : UiState<Nothing>()

    /**
     * Represents the UI in a loading state, typically while waiting for an asynchronous
     * operation like a network call to complete.
     */
    data object Loading : UiState<Nothing>()

    /**
     * Represents the UI in a success state, holding the data that was successfully loaded.
     * @param data The data to be displayed on the UI.
     */
    data class Success<out T>(val data: T) : UiState<T>()

    /**
     * Represents the UI in an error state, holding an error message to be displayed.
     * @param message A user-friendly message describing the error.
     */
    data class Error(val message: String) : UiState<Nothing>()
}