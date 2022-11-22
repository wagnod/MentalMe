package com.wagnod.navigation.di

import com.wagnod.core_ui.friends.FriendsNavigator
import com.wagnod.core_ui.home.HomeNavigator
import com.wagnod.core_ui.login.LoginNavigator
import com.wagnod.core_ui.new_screen.NewNavigator
import com.wagnod.core_ui.profile.ProfileNavigator
import com.wagnod.core_ui.search.SearchNavigator
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