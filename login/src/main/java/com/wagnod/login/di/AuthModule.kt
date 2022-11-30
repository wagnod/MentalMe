package com.wagnod.login.di

import com.wagnod.data.login.FirebaseRepositoryImpl
import com.wagnod.domain.login.repository.FirebaseRepository
import com.wagnod.domain.login.usecase.CheckIsUserAuthorizedUseCase
import com.wagnod.domain.login.usecase.SignInUseCase
import com.wagnod.domain.login.usecase.SignUpUseCase
import com.wagnod.login.ui.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    // repository
    factory<FirebaseRepository> { FirebaseRepositoryImpl(get(), get()) }

    // useCase
    factory { SignUpUseCase(get()) }
    factory { SignInUseCase(get()) }
    factory { CheckIsUserAuthorizedUseCase(get()) }

    // viewModel
    viewModel { AuthViewModel(get(), get(), get()) }
}