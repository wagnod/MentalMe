package com.wagnod.core_ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wagnod.core_ui.friends.FriendsNavigator
import com.wagnod.core_ui.home.HomeNavigator
import com.wagnod.core_ui.login.LoginNavigator
import com.wagnod.core_ui.new_screen.NewNavigator
import com.wagnod.core_ui.profile.ProfileNavigator
import com.wagnod.core_ui.search.SearchNavigator

interface Navigator {

    val homeNavigator: HomeNavigator
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

    @Composable
    fun checkDestination(): Boolean
}