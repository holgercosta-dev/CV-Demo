package com.example.cv_demo.di

import com.example.cv_demo.data.di.dataModule
import com.example.cv_demo.domain.di.domainModule
import com.example.cv_demo.presentation.di.presentationModule
import org.koin.dsl.module

val appModule = module {
    includes(dataModule, domainModule, presentationModule)
}
