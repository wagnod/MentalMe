package com.wagnod.profile.di

import com.wagnod.data.FirebaseDatabaseRepositoryImpl
import com.wagnod.data.login.FirebaseRepositoryImpl
import com.wagnod.domain.FirebaseDatabaseRepository
import com.wagnod.domain.home.usecase.GetUserInfoUseCase
import com.wagnod.domain.login.repository.FirebaseRepository
import com.wagnod.profile.ui.ProfileViewModel
import com.wagnod.profile.ui.ProfileViewModelMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    // repository
    factory<FirebaseRepository> { FirebaseRepositoryImpl(get(), get(), get()) }
    factory<FirebaseDatabaseRepository> { FirebaseDatabaseRepositoryImpl(get(), get()) }

    // useCase
    factory { GetUserInfoUseCase(get()) }

    // mapper
    factory { ProfileViewModelMapper() }

    // viewModel
    viewModel { ProfileViewModel(get(), get()) }
}