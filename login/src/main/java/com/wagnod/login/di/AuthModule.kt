package com.wagnod.login.di

import com.wagnod.core_ui.navigators.LoginNavigator
import com.wagnod.login.navigation.LoginNavigatorImpl
import com.wagnod.login.ui.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single<LoginNavigator> { LoginNavigatorImpl() }
    // viewModel
    viewModel { AuthViewModel(get(), get(), get(), get()) }
}