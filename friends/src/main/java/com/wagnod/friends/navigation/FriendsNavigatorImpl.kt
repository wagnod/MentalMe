package com.wagnod.friends.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.wagnod.core_ui.navigators.FriendsNavigator
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.friends.FriendsScreen.FriendsMainScreen

class FriendsNavigatorImpl : FriendsNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
        navGraphBuilder.navigation(FriendsMainScreen.route, FriendsMainScreen.rootRoute) {
            FriendsMainScreen.createScreen(navGraphBuilder, navigator)
        }
    }
}