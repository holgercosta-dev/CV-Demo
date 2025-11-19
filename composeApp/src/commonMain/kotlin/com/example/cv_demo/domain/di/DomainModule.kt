package com.example.cv_demo.domain.di

import com.example.cv_demo.domain.use_case.product.GetProductDetails
import com.example.cv_demo.domain.use_case.product.GetProductDetailsType
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetProductDetails) { bind<GetProductDetailsType>() }
}