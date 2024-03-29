package com.wagnod.mentalme.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wagnod.core_ui.di.coreUIModule
import com.wagnod.dashboard.di.dashboardModule
import com.wagnod.data.di.dataModule
import com.wagnod.domain.di.domainModule
import com.wagnod.entries.di.entriesModule
import com.wagnod.history.di.historyModule
import com.wagnod.home.di.homeModule
import com.wagnod.home.goals.di.goalsModule
import com.wagnod.login.di.authModule
import com.wagnod.navigation.di.navigationModules
import com.wagnod.profile.di.profileModule
import com.wagnod.explore.di.exploreModule
import org.koin.dsl.module

private val mainActivityModule = module {

    single { Firebase.auth }
    single { Firebase.database("https://mentalme-d9b0c-default-rtdb.europe-west1.firebasedatabase.app") }
}

val appModules = listOf(
    mainActivityModule,
    navigationModules,
    authModule,
    dataModule,
    profileModule,
    goalsModule,
    homeModule,
    dashboardModule,
    coreUIModule,
    domainModule,
    entriesModule,
    historyModule,
    exploreModule
)
