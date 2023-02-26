package com.wagnod.core_ui.navigators.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wagnod.core_ui.navigators.FriendsNavigator
import com.wagnod.core_ui.navigators.DashboardNavigator
import com.wagnod.core_ui.navigators.LoginNavigator
import com.wagnod.core_ui.navigators.NewNavigator
import com.wagnod.core_ui.navigators.ProfileNavigator
import com.wagnod.core_ui.navigators.SearchNavigator

interface Navigator {

    val dashboardNavigator: DashboardNavigator
    val searchNavigator: SearchNavigator
    val newNavigator: NewNavigator
    val friendsNavigator: FriendsNavigator
    val profileNavigator: ProfileNavigator
    val loginNavigator: LoginNavigator

    @Composable
    fun buildNavHost()
    fun setNavController(navController: NavHostController)

    fun back()

    fun navigateToHome()
    fun navigateToSearch()
    fun navigateToNew()
    fun navigateToFriends()
    fun navigateToProfile()
    fun navigateToLogin()
    fun navigateToHomeAndClear()

    @Composable
    fun checkDestination(): Boolean

    @Composable
    fun currentRoute(): String?
}