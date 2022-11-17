package com.wagnod.mentalme.navigation.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wagnod.mentalme.navigation.Navigator
import com.wagnod.mentalme.ui.search.Search
import com.wagnod.mentalme.ui.data.NavSections

class SearchNavigatorImpl : SearchNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
        navGraphBuilder.navigation(NavSections.SEARCH.route, rootRoute) {
            composable(NavSections.SEARCH.route) { Search() }
        }
    }

    companion object {
        const val rootRoute = "search_root"
    }
}