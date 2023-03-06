package com.wagnod.core_ui.navigators.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.wagnod.core_ui.base_screen.Screen
import com.wagnod.core_ui.navigators.FriendsNavigator
import com.wagnod.core_ui.navigators.DashboardNavigator
import com.wagnod.core_ui.navigators.LoginNavigator
import com.wagnod.core_ui.navigators.EntriesNavigator
import com.wagnod.core_ui.navigators.ProfileNavigator
import com.wagnod.core_ui.navigators.SearchNavigator

interface Navigator {

    val dashboardNavigator: DashboardNavigator
    val searchNavigator: SearchNavigator
    val entriesNavigator: EntriesNavigator
    val friendsNavigator: FriendsNavigator
    val profileNavigator: ProfileNavigator
    val loginNavigator: LoginNavigator

    @Composable
    fun buildNavHost()
    fun setNavController(navController: NavHostController)
    fun setGraphs(navGraphBuilder: NavGraphBuilder)

    fun back()

    fun navigateToHome()
    fun navigateToSearch()
    fun navigateToEntries()
    fun navigateToFriends()
    fun navigateToProfile()
    fun navigateToLogin()

    @Composable
    fun currentScreen(): Screen<*>

    @Composable
    fun currentRoute(): String?
}