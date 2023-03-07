package com.wagnod.entries.di

import com.wagnod.core_ui.navigators.EntriesNavigator
import com.wagnod.entries.navigation.EntriesNavigatorImpl
import com.wagnod.entries.ui.EntriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val entriesModule = module {
    viewModel {
        EntriesViewModel(get(), get())
    }
    single<EntriesNavigator> { EntriesNavigatorImpl() }
}