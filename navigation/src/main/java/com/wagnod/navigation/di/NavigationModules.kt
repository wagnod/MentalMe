package com.wagnod.navigation.di

import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.navigation.NavigatorImpl
import org.koin.dsl.module

val navigationModules = module {
    single<Navigator> {
        NavigatorImpl(get(), get(), get(), get(), get(), get())
    }
}