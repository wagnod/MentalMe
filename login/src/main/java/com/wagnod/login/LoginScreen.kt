package com.wagnod.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wagnod.core_ui.base_screen.Screen
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.login.navigation.LoginScreenTypes.*
import com.wagnod.login.ui.auth.AuthScreen

sealed class LoginScreen<T>(
    override val route: String,
    override val rootRoute: String = Companion.rootRoute,
    override val showBottomNavigation: Boolean = false
) : Screen<T>() {

    object LoginMainScreen : LoginScreen<Unit>(LOGIN_MAIN_SCREEN.route, rootRoute) {
        override fun navigate(args: Unit, navController: NavController) {
            navController.navigateAndMakeStart(route)
        }

        override fun createScreen(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
            navGraphBuilder.composable(route) {
                AuthScreen(navigator)
            }
        }
    }

    companion object {
        const val rootRoute = "login"
    }
}

