package com.example.cv_demo.presentation.di

import com.example.cv_demo.presentation.reactive_interactive.state.ProductDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::ProductDetailsViewModel)
}