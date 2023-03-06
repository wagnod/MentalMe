package com.wagnod.friends

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wagnod.core_ui.base_screen.Screen
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.friends.navigation.FriendsScreenTypes

sealed class FriendsScreen<T>(
    override val route: String,
    override val rootRoute: String = Companion.rootRoute,
    override val showBottomNavigation: Boolean = true
) : Screen<T>() {

    object FriendsMainScreen : FriendsScreen<Unit>(
        FriendsScreenTypes.FRIENDS_MAIN_SCREEN.route,
        rootRoute
    ) {
        override fun navigate(args: Unit, navController: NavController) {
            navController.navigate(route) { navController.navigateAndMakeStart(route) }
        }

        override fun createScreen(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
            navGraphBuilder.composable(route) {
                FriendsScreen(navigator)
            }
        }
    }

    companion object {
        const val rootRoute = "friends"
    }
}