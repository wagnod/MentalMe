package com.wagnod.mentalme.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wagnod.core_ui.Navigator
import com.wagnod.login.di.authModule
import com.wagnod.navigation.NavigatorImpl
import com.wagnod.navigation.di.navigationModules
import org.koin.dsl.module

private val mainActivityModule = module {

    single { Firebase.auth }

    single<Navigator> {
        NavigatorImpl(get(), get(), get(), get(), get(), get())
    }
}

val appModules = listOf(
    mainActivityModule,
    navigationModules,
    authModule
)
