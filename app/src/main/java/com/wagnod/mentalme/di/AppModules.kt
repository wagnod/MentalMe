package com.wagnod.mentalme.di

import com.wagnod.core_ui.Navigator
import com.wagnod.navigation.NavigatorImpl
import org.koin.dsl.module

private val mainActivityModule = module {
    single<Navigator> {
        NavigatorImpl()
    }
}

val appModules = listOf(
    mainActivityModule
)
