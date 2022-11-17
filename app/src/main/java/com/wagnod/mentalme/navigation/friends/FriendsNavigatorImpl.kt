package com.wagnod.mentalme.navigation.friends

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wagnod.mentalme.navigation.Navigator
import com.wagnod.mentalme.ui.data.NavSections
import com.wagnod.mentalme.ui.friends.Friends

class FriendsNavigatorImpl: FriendsNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
        navGraphBuilder.navigation(NavSections.FRIENDS.route, rootRoute) {
            composable(NavSections.FRIENDS.route) { Friends() }
        }
    }

    companion object {
        const val rootRoute = "friends_root"
    }
}