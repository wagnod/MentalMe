package com.wagnod.profile.di

import com.wagnod.core_ui.navigators.ProfileNavigator
import com.wagnod.profile.navigation.ProfileNavigatorImpl
import com.wagnod.profile.ui.ProfileViewModel
import com.wagnod.profile.ui.ProfileViewModelMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    single<ProfileNavigator> { ProfileNavigatorImpl() }

    // mapper
    factory { ProfileViewModelMapper() }

    // viewModel
    viewModel { ProfileViewModel(get(), get()) }
}