package com.wagnod.mentalme.navigation.new

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wagnod.mentalme.navigation.Navigator
import com.wagnod.mentalme.ui.new_screen.NewScreen
import com.wagnod.mentalme.ui.data.NavSections

class NewNavigatorImpl : NewNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
        navGraphBuilder.navigation(NavSections.NEW.route, rootRoute) {
            composable(NavSections.NEW.route) { NewScreen() }
        }
    }

    private companion object {
        const val rootRoute = "new_root"
    }

}