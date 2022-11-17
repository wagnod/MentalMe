package com.wagnod.navigation.new

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wagnod.core_ui.Navigator
import com.wagnod.core_ui.new_screen.NewNavigator
import com.wagnod.navigation.data.NavSections
import com.wagnod.new_screen.NewScreen

class NewNavigatorImpl : NewNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: com.wagnod.core_ui.Navigator) {
        navGraphBuilder.navigation(NavSections.NEW.route, rootRoute) {
            composable(NavSections.NEW.route) { NewScreen() }
        }
    }

    private companion object {
        const val rootRoute = "new_root"
    }

}