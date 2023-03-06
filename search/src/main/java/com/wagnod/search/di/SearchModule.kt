package com.wagnod.search.di

import com.wagnod.core_ui.navigators.SearchNavigator
import com.wagnod.search.navigation.SearchNavigatorImpl
import org.koin.dsl.module

val searchModule = module {
    single<SearchNavigator> { SearchNavigatorImpl() }
}