package com.wagnod.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wagnod.core_ui.base_screen.Screen
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.search.navigation.SearchScreenTypes
import com.wagnod.search.ui.Search


sealed class SearchScreen<T>(
    override val route: String,
    override val rootRoute: String = Companion.rootRoute,
    override val showBottomNavigation: Boolean = true
) : Screen<T>() {

    object SearchMainScreen : SearchScreen<Unit>(
        SearchScreenTypes.SEARCH_MAIN_SCREEN.route,
        rootRoute
    ) {
        override fun navigate(args: Unit, navController: NavController) {
            navController.navigateOrPopup(route) { navController.navigateAndMakeStart(route) }
        }

        override fun createScreen(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
            navGraphBuilder.composable(route) {
                Search(navigator)
            }
        }

    }

    companion object {
        const val rootRoute = "search"
    }
}