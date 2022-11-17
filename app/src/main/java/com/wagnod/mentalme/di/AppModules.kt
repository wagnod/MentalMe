package com.wagnod.mentalme.di

import com.wagnod.mentalme.navigation.Navigator
import com.wagnod.mentalme.navigation.NavigatorImpl
import org.koin.dsl.module

private val mainActivityModule = module {
    single<Navigator> {
        NavigatorImpl()
    }
}

val appModules = listOf(
    mainActivityModule
)
