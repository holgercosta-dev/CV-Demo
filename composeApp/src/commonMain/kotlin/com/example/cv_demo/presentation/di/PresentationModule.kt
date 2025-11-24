package com.example.cv_demo.presentation.di

import com.example.cv_demo.presentation.reactive_interactive.state.ProductDetailsViewModel
import com.example.cv_demo.presentation.reactive_interactive.state.SummaryMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    factoryOf(::SummaryMapper)
    viewModelOf(::ProductDetailsViewModel)
}