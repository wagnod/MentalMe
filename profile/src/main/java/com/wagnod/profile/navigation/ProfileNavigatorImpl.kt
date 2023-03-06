package com.wagnod.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.wagnod.core.datastore.user.UserInfo
import com.wagnod.core_ui.navigators.ProfileNavigator
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.profile.ProfileScreen.ProfileEditScreen
import com.wagnod.profile.ProfileScreen.ProfileMainScreen

class ProfileNavigatorImpl : ProfileNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun navigateToEditScreen(user: UserInfo) {
        ProfileEditScreen.navigate(user, mNavController)
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
        navGraphBuilder.navigation(ProfileMainScreen.route, ProfileMainScreen.rootRoute) {
            ProfileMainScreen.createScreen(navGraphBuilder, navigator)
            ProfileEditScreen.createScreen(this, navigator)
        }
    }
}