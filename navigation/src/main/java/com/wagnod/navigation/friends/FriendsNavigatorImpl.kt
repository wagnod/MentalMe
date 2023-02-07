package com.wagnod.navigation.friends

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.navigators.FriendsNavigator
import com.wagnod.friends.FriendsScreenContent
import com.wagnod.navigation.data.NavSections

class FriendsNavigatorImpl: FriendsNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
        navGraphBuilder.navigation(NavSections.FRIENDS.route, rootRoute) {
            composable(NavSections.FRIENDS.route) { FriendsScreenContent() }
        }
    }

    companion object {
        const val rootRoute = "friends_root"
    }
}