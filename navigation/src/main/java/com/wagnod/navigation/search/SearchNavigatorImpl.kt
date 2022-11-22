package com.wagnod.navigation.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wagnod.core_ui.Navigator
import com.wagnod.core_ui.search.SearchNavigator
import com.wagnod.navigation.data.NavSections
import com.wagnod.search.Search

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