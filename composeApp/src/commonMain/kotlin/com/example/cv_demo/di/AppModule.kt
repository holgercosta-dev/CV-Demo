package com.example.cv_demo.di

import com.example.cv_demo.data.di.dataModule
import org.koin.dsl.module

val appModule = module {
    includes(dataModule)
}
