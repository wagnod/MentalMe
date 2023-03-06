package com.wagnod.entries

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wagnod.core_ui.base_screen.Screen
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.entries.navigation.EntriesScreenTypes
import com.wagnod.entries.ui.EntriesMainScreen

sealed class EntriesScreen<T>(
    override val route: String,
    override val rootRoute: String = Companion.rootRoute,
    override val showBottomNavigation: Boolean = true
) : Screen<T>() {

    object EntriesMainScreen : EntriesScreen<Unit>(
        EntriesScreenTypes.ENTRIES_MAIN_SCREEN.route,
        rootRoute
    ) {
        override fun navigate(args: Unit, navController: NavController) {
            navController.navigate(route)
        }

        override fun createScreen(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
            navGraphBuilder.composable(route) {
                EntriesMainScreen(navigator)
            }
        }
    }

    companion object {
        const val rootRoute = "entries"
    }
}