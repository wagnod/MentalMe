package com.wagnod.mentalme.navigation.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wagnod.mentalme.navigation.Navigator
import com.wagnod.mentalme.ui.profile.Profile
import com.wagnod.mentalme.ui.data.NavSections

class ProfileNavigatorImpl : ProfileNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
        navGraphBuilder.navigation(NavSections.PROFILE.route, rootRoute) {
            composable(NavSections.PROFILE.route) { Profile() }
        }
    }

    companion object {
        const val rootRoute = "profile_root"
    }
}