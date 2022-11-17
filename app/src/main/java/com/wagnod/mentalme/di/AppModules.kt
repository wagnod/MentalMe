package com.wagnod.mentalme.di

import com.wagnod.core_ui.Navigator
import com.wagnod.navigation.NavigatorImpl
import com.wagnod.navigation.di.navigationModules
import org.koin.dsl.module

private val mainActivityModule = module {
    single<Navigator> {
        NavigatorImpl(get(), get(), get(), get(), get())
    }
}

val appModules = listOf(
    mainActivityModule,
    navigationModules
)
