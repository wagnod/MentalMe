package com.wagnod.history.di

import com.wagnod.core_ui.navigators.HistoryNavigator
import com.wagnod.history.navigation.HistoryNavigatorImpl
import com.wagnod.history.ui.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val historyModule = module {
    viewModel {
        HistoryViewModel(get(), get())
    }
    single<HistoryNavigator> { HistoryNavigatorImpl() }
}