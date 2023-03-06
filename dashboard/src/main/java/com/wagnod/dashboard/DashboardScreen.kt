package com.wagnod.dashboard

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wagnod.core.datastore.articles.Article
import com.wagnod.core_ui.Keys
import com.wagnod.core_ui.base_screen.Screen
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.dashboard.navigation.DashboardScreenTypes
import com.wagnod.dashboard.ui.DashboardMainScreen
import com.wagnod.dashboard.ui.article.ArticleMainScreen
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

sealed class DashboardScreen<T>(
    override val route: String,
    override val rootRoute: String = Companion.rootRoute,
    override val showBottomNavigation: Boolean = true
) : Screen<T>() {

    object DashboardMainScreen : DashboardScreen<Unit>(
        DashboardScreenTypes.DASHBOARD_MAIN_SCREEN.route,
        rootRoute
    ) {
        override fun navigate(args: Unit, navController: NavController) {
            navController.navigateOrPopup(route) { navController.navigateAndMakeStart(route) }
        }

        override fun createScreen(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
            navGraphBuilder.composable(route) {
                DashboardMainScreen(navigator)
            }
        }

    }

    object ArticleMainScreen: DashboardScreen<Article>(
        DashboardScreenTypes.ARTICLE.route,
        rootRoute,
        false
    ) {
        override fun navigate(args: Article, navController: NavController) {
            navController.navigate(
                route = route.replace(
                    "{${Keys.ARG_ARTICLE}}",
                    Json.encodeToString(args)
                )
            )
        }

        override fun createScreen(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
            navGraphBuilder.composable(
                route = route,
                arguments = listOf(
                    navArgument(Keys.ARG_ARTICLE) { type = NavType.StringType }
                )
            ) {
                val articleJson = it.arguments?.getString(Keys.ARG_ARTICLE).orEmpty()
                val article = Json.decodeFromString<Article>(articleJson)
                ArticleMainScreen(article, navigator)
            }
        }
    }

    companion object {
        const val rootRoute = "dashboard"
    }
}