package com.wagnod.explore.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.wagnod.core_ui.navigators.ExploreNavigator
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.explore.ExploreScreen.ExploreMainScreen

class ExploreNavigatorImpl : ExploreNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
        navGraphBuilder.navigation(ExploreMainScreen.route, ExploreMainScreen.rootRoute) {
            ExploreMainScreen.createScreen(navGraphBuilder, navigator)
        }
    }
}