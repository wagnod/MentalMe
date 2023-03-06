package com.wagnod.entries.di

import com.wagnod.core_ui.navigators.EntriesNavigator
import com.wagnod.entries.navigation.EntriesNavigatorImpl
import org.koin.dsl.module

val entriesModule = module {
    single<EntriesNavigator> { EntriesNavigatorImpl() }
}