package com.wagnod.dashboard.di

import com.wagnod.core_ui.navigators.DashboardNavigator
import com.wagnod.dashboard.navigation.DashboardNavigatorImpl
import com.wagnod.dashboard.ui.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardModule = module {
    single<DashboardNavigator> { DashboardNavigatorImpl() }
    viewModel { DashboardViewModel(get(), get(), get(), get()) }
}