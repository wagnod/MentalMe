package com.wagnod.data.di

import com.wagnod.data.firebase.FirebaseDatabaseReferencesImpl
import com.wagnod.data.firebase.FirebaseDatabaseRepositoryImpl
import com.wagnod.data.dashboard.DashboardRepositoryImpl
import com.wagnod.data.explore.ExploreRepositoryImpl
import com.wagnod.data.login.FirebaseRepositoryImpl
import com.wagnod.domain.AppDispatchers
import com.wagnod.domain.AppDispatchersImpl
import com.wagnod.domain.firebase.FirebaseDatabaseReferences
import com.wagnod.domain.firebase.FirebaseDatabaseRepository
import com.wagnod.domain.dashboard.repository.DashboardRepository
import com.wagnod.domain.explore.repository.ExploreRepository
import com.wagnod.domain.login.repository.FirebaseRepository
import org.koin.dsl.module

val dataModule = module {
    factory<AppDispatchers> { AppDispatchersImpl() }
    factory<FirebaseDatabaseReferences> { FirebaseDatabaseReferencesImpl(get()) }
    factory<FirebaseDatabaseRepository> { FirebaseDatabaseRepositoryImpl(get(), get(), get()) }
    factory<FirebaseRepository> { FirebaseRepositoryImpl(get(), get(), get()) }
    single<DashboardRepository> { DashboardRepositoryImpl(get(), get()) }
    single<ExploreRepository> { ExploreRepositoryImpl(get(), get()) }
}