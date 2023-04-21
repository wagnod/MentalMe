package com.wagnod.domain.di

import com.wagnod.domain.app.InitAppDataUseCase
import com.wagnod.domain.dashboard.usecase.*
import com.wagnod.domain.explore.usecase.GetCategoriesUseCase
import com.wagnod.domain.explore.usecase.SubscribeCategoriesUseCase
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
    factory { SubscribeDailiesUseCase(get()) }
    factory { GetDailiesUseCase(get()) }
    factory { GetCategoriesUseCase(get()) }
    factory { SubscribeCategoriesUseCase(get()) }
    single { InitAppDataUseCase(get(), get(), get()) }
}