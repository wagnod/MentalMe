package com.wagnod.explore.di

import com.wagnod.core_ui.navigators.ExploreNavigator
import com.wagnod.explore.navigation.ExploreNavigatorImpl
import com.wagnod.explore.ui.ExploreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val exploreModule = module {
    single<ExploreNavigator> { ExploreNavigatorImpl() }
    viewModel { ExploreViewModel(get(), get()) }
}