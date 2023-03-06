package com.wagnod.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wagnod.core.datastore.articles.Article
import com.wagnod.core.datastore.user.UserInfo
import com.wagnod.core_ui.Keys
import com.wagnod.core_ui.base_screen.Screen
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.profile.navigation.ProfileScreenTypes
import com.wagnod.profile.ui.ProfileScreen
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

sealed class ProfileScreen<T>(
    override val route: String,
    override val rootRoute: String = Companion.rootRoute,
    override val showBottomNavigation: Boolean = true
) : Screen<T>() {

    object ProfileMainScreen : ProfileScreen<Unit>(
        ProfileScreenTypes.PROFILE_MAIN_SCREEN.route,
        rootRoute
    ) {
        override fun navigate(args: Unit, navController: NavController) {
            navController.navigateOrPopup(route)
        }

        override fun createScreen(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
            navGraphBuilder.composable(route) {
                ProfileScreen(navigator)
            }
        }

    }

    object ProfileEditScreen : ProfileScreen<UserInfo>(
        ProfileScreenTypes.PROFILE_EDIT_SCREEN.route,
        rootRoute,
        false
    ) {
        override fun navigate(args: UserInfo, navController: NavController) {
            navController.navigate(
                route = route.replace(
                    "{${Keys.ARG_USER}}",
                    Json.encodeToString(args)
                )
            )
        }

        override fun createScreen(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
            navGraphBuilder.composable(
                route = route,
                arguments = listOf(
                    navArgument(Keys.ARG_USER) { type = NavType.StringType }
                )
            ) {
                val userJson = it.arguments?.getString(Keys.ARG_USER).orEmpty()
                val article = Json.decodeFromString<Article>(userJson)
                ProfileScreen(navigator)
            }
        }

    }

    companion object {
        const val rootRoute = "profile"
    }
}