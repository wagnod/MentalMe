package com.wagnod.navigation.di

import com.wagnod.core_ui.navigators.FriendsNavigator
import com.wagnod.core_ui.navigators.HomeNavigator
import com.wagnod.core_ui.navigators.LoginNavigator
import com.wagnod.core_ui.navigators.NewNavigator
import com.wagnod.core_ui.navigators.ProfileNavigator
import com.wagnod.core_ui.navigators.SearchNavigator
import com.wagnod.navigation.friends.FriendsNavigatorImpl
import com.wagnod.navigation.home.HomeNavigatorImpl
import com.wagnod.navigation.login.LoginNavigatorImpl
import com.wagnod.navigation.new.NewNavigatorImpl
import com.wagnod.navigation.profile.ProfileNavigatorImpl
import com.wagnod.navigation.search.SearchNavigatorImpl
import org.koin.dsl.module

val navigationModules = module {
    single<HomeNavigator> { HomeNavigatorImpl() }
    single<SearchNavigator> { SearchNavigatorImpl() }
    single<NewNavigator> { NewNavigatorImpl() }
    single<FriendsNavigator> { FriendsNavigatorImpl() }
    single<ProfileNavigator> { ProfileNavigatorImpl() }
    single<LoginNavigator> { LoginNavigatorImpl() }
}