package com.wagnod.navigation.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wagnod.core_ui.profile.ProfileNavigator
import com.wagnod.navigation.data.NavSections
import com.wagnod.profile.ui.ProfileScreen

class ProfileNavigatorImpl : ProfileNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: com.wagnod.core_ui.Navigator) {
        navGraphBuilder.navigation(NavSections.PROFILE.route, rootRoute) {
            composable(NavSections.PROFILE.route) { ProfileScreen() }
//            composable(ProfileNavRoutes.EDIT_PROFILE_ROUTE.route) { EditProfileScreen()}
        }
    }

    override fun navigateToEditScreen() {
        mNavController.navigate(ProfileNavRoutes.EDIT_PROFILE_ROUTE.route)
    }

    companion object {
        const val rootRoute = "profile_root"
    }
}