package com.wagnod.history

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wagnod.core_ui.base_screen.Screen
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.history.navigation.HistoryScreenTypes
import com.wagnod.history.ui.HistoryMainScreen

sealed class HistoryScreen<T>(
    override val route: String,
    override val rootRoute: String = Companion.rootRoute,
    override val showBottomNavigation: Boolean = true
) : Screen<T>() {

    object HistoryMainScreen : HistoryScreen<Unit>(
        HistoryScreenTypes.HISTORY_MAIN_SCREEN.route,
        rootRoute
    ) {
        override fun navigate(args: Unit, navController: NavController) {
            navController.navigate(route) { navController.navigateAndMakeStart(route) }
        }

        override fun createScreen(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
            navGraphBuilder.composable(route) {
                HistoryMainScreen(navigator)
            }
        }
    }

    companion object {
        const val rootRoute = "friends"
    }
}