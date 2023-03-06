package com.wagnod.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.wagnod.core_ui.navigators.SearchNavigator
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.search.SearchScreen.SearchMainScreen

class SearchNavigatorImpl : SearchNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
        navGraphBuilder.navigation(SearchMainScreen.route, SearchMainScreen.rootRoute) {
            SearchMainScreen.createScreen(navGraphBuilder, navigator)
        }
    }
}