package com.wagnod.data.di

import com.wagnod.data.AppDispatchers
import com.wagnod.data.AppDispatchersImpl
import org.koin.dsl.module

val dataModule = module {
    factory<AppDispatchers> { AppDispatchersImpl() }
}