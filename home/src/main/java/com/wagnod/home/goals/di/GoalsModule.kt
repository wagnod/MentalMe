package com.wagnod.home.goals.di

import com.wagnod.data.FirebaseDatabaseRepositoryImpl
import com.wagnod.data.login.FirebaseRepositoryImpl
import com.wagnod.domain.FirebaseDatabaseRepository
import com.wagnod.domain.home.usecase.GetUserInfoUseCase
import com.wagnod.domain.home.usecase.PutGoalsToDatabaseUseCase
import com.wagnod.domain.login.repository.FirebaseRepository
import com.wagnod.home.goals.GoalsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val goalsModule = module {
    // repository
    factory<FirebaseRepository> { FirebaseRepositoryImpl(get(), get(), get()) }
    factory<FirebaseDatabaseRepository> { FirebaseDatabaseRepositoryImpl(get(), get(), get()) }

    // useCase
    factory { GetUserInfoUseCase(get()) }
    factory { PutGoalsToDatabaseUseCase(get()) }

    // viewModel
    viewModel { GoalsViewModel(get(), get()) }
}
