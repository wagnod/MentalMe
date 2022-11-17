package com.wagnod.mentalme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wagnod.mentalme.navigation.friends.FriendsNavigator
import com.wagnod.mentalme.navigation.home.HomeNavigator
import com.wagnod.mentalme.navigation.new.NewNavigator
import com.wagnod.mentalme.navigation.profile.ProfileNavigator
import com.wagnod.mentalme.navigation.search.SearchNavigator

interface Navigator {

    val homeNavigator: HomeNavigator
    val searchNavigator: SearchNavigator
    val newNavigator: NewNavigator
    val friendsNavigator: FriendsNavigator
    val profileNavigator: ProfileNavigator

    @Composable
    fun buildNavHost()
    fun setNavController(navController: NavHostController)

    fun navigateToHome()
    fun navigateToSearch()
    fun navigateToNew()
    fun navigateToFriends()
    fun navigateToProfile()
}