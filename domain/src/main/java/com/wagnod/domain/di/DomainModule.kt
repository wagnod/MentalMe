package com.wagnod.domain.di

import com.wagnod.domain.app.InitAppDataUseCase
import com.wagnod.domain.dashboard.usecase.GetTodaySelectionUseCase
import com.wagnod.domain.dashboard.usecase.GetUserInfoUseCase
import com.wagnod.domain.dashboard.usecase.PutGoalsToDatabaseUseCase
import com.wagnod.domain.dashboard.usecase.SubscribeTodaySelectionUseCase
import com.wagnod.domain.login.usecase.CheckIsUserAuthorizedUseCase
import com.wagnod.domain.login.usecase.SignInUseCase
import com.wagnod.domain.login.usecase.SignUpUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetUserInfoUseCase(get()) }
    factory { PutGoalsToDatabaseUseCase(get()) }
    single { CheckIsUserAuthorizedUseCase(get()) }
    factory { SignInUseCase(get()) }
    factory { SignUpUseCase(get()) }
    factory { SubscribeTodaySelectionUseCase(get()) }
    factory { GetTodaySelectionUseCase(get()) }
    single { InitAppDataUseCase(get(), get()) }
}