package com.wagnod.friends.di

import com.wagnod.core_ui.navigators.FriendsNavigator
import com.wagnod.friends.navigation.FriendsNavigatorImpl
import org.koin.dsl.module

val friendsModule = module {
    single<FriendsNavigator> { FriendsNavigatorImpl() }
}