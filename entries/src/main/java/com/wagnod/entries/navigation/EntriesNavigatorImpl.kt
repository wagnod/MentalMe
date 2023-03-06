package com.wagnod.entries.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.wagnod.core_ui.navigators.EntriesNavigator
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.entries.EntriesScreen.EntriesMainScreen

class EntriesNavigatorImpl : EntriesNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
        navGraphBuilder.navigation(
            EntriesMainScreen.route,
            EntriesMainScreen.rootRoute
        ) {
            EntriesMainScreen.createScreen(navGraphBuilder, navigator)
        }
    }
}