package com.wagnod.navigation.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.navigators.LoginNavigator
import com.wagnod.login.ui.auth.AuthScreen
import com.wagnod.navigation.data.LoginRoute

class LoginNavigatorImpl : LoginNavigator {

    private lateinit var mNavController: NavController
    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
        navGraphBuilder.navigation(LoginRoute.route, rootRoute) {
            composable(LoginRoute.route) { AuthScreen(navigator) }
        }
    }

    override fun navigateToSignUp() {
        mNavController.navigate(LoginRoute.singUpRoute)
    }

    companion object {
        const val rootRoute = "login_root"
    }
}