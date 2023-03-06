package com.wagnod.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.wagnod.core_ui.navigators.LoginNavigator
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.login.LoginScreen.LoginMainScreen

class LoginNavigatorImpl : LoginNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
        navGraphBuilder.navigation(LoginMainScreen.route, LoginMainScreen.rootRoute) {
            LoginMainScreen.createScreen(navGraphBuilder, navigator)
        }
    }
}