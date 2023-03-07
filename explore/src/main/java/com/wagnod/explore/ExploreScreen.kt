package com.wagnod.explore

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wagnod.core_ui.base_screen.Screen
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.explore.navigation.ExploreScreenTypes
import com.wagnod.explore.ui.ExploreMainScreen


sealed class ExploreScreen<T>(
    override val route: String,
    override val rootRoute: String = Companion.rootRoute,
    override val showBottomNavigation: Boolean = true
) : Screen<T>() {

    object ExploreMainScreen : ExploreScreen<Unit>(
        ExploreScreenTypes.EXPLORE_MAIN_SCREEN.route,
        rootRoute
    ) {
        override fun navigate(args: Unit, navController: NavController) {
            navController.navigateOrPopup(route) { navController.navigateAndMakeStart(route) }
        }

        override fun createScreen(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
            navGraphBuilder.composable(route) {
                ExploreMainScreen(navigator)
            }
        }

    }

    companion object {
        const val rootRoute = "search"
    }
}