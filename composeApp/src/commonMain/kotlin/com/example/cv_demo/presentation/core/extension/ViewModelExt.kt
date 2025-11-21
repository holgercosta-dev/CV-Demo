package com.example.cv_demo.presentation.core.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

context(viewModel: ViewModel)
fun <T> MutableSharedFlow<T>.send(value: T) {
    viewModel.viewModelScope.launch {
        emit(value)
    }
}