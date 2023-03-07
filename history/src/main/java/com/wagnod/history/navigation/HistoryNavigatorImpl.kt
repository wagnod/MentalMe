package com.wagnod.history.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.wagnod.core_ui.navigators.HistoryNavigator
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.history.HistoryScreen.HistoryMainScreen

class HistoryNavigatorImpl : HistoryNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
        navGraphBuilder.navigation(HistoryMainScreen.route, HistoryMainScreen.rootRoute) {
            HistoryMainScreen.createScreen(navGraphBuilder, navigator)
        }
    }
}