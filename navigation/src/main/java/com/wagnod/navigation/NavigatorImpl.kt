package com.wagnod.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.wagnod.core_ui.base_screen.Screen
import com.wagnod.core_ui.navigators.*
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.dashboard.DashboardScreen
import com.wagnod.entries.EntriesScreen
import com.wagnod.history.HistoryScreen
import com.wagnod.login.LoginScreen
import com.wagnod.profile.ProfileScreen
import com.wagnod.explore.ExploreScreen

class NavigatorImpl(
    override val dashboardNavigator: DashboardNavigator,
    override val exploreNavigator: ExploreNavigator,
    override val entriesNavigator: EntriesNavigator,
    override val historyNavigator: HistoryNavigator,
    override val profileNavigator: ProfileNavigator,
    override val loginNavigator: LoginNavigator
) : Navigator {

    private lateinit var mNavController: NavHostController

    private val navigators = listOf(
        dashboardNavigator,
        exploreNavigator,
        entriesNavigator,
        historyNavigator,
        profileNavigator,
        loginNavigator
    )

    override fun setNavController(navController: NavHostController) {
        mNavController = navController
        navigators.forEach {
            it.setNavController(navController)
        }
    }

    override fun back() {
        mNavController.navigateUp()
    }

    override fun setGraphs(navGraphBuilder: NavGraphBuilder) {
        navigators.forEach {
            it.setGraph(navGraphBuilder, this@NavigatorImpl)
        }
    }

    @Composable
    override fun buildNavHost() {
        NavHost(
            navController = mNavController,
            startDestination = LoginScreen.LoginMainScreen.route
        ) {
            navigators.forEach {
                it.setGraph(this, this@NavigatorImpl)
            }
        }
    }

    override fun navigateToHome() {
        DashboardScreen.DashboardMainScreen.navigate(Unit, mNavController)
    }

    override fun navigateToSearch() {
        ExploreScreen.ExploreMainScreen.navigate(Unit, mNavController)
    }

    override fun navigateToEntries() {
        EntriesScreen.EntriesMainScreen.navigate(Unit, mNavController)
    }

    override fun navigateToFriends() {
        HistoryScreen.HistoryMainScreen.navigate(Unit, mNavController)
    }

    override fun navigateToProfile() {
        ProfileScreen.ProfileMainScreen.navigate(Unit, mNavController)
    }

    override fun navigateToLogin() {
        LoginScreen.LoginMainScreen.navigate(Unit, mNavController)
    }

    @Composable
    override fun currentScreen(): Screen<*> {
        val entry = mNavController.currentBackStackEntryAsState()
        return when (entry.value?.destination?.route) {
            ProfileScreen.ProfileMainScreen.route -> ProfileScreen.ProfileMainScreen
            DashboardScreen.DashboardMainScreen.route -> DashboardScreen.DashboardMainScreen
            DashboardScreen.ArticleMainScreen.route -> DashboardScreen.ArticleMainScreen
            EntriesScreen.EntriesMainScreen.route -> EntriesScreen.EntriesMainScreen
            HistoryScreen.HistoryMainScreen.route -> HistoryScreen.HistoryMainScreen
            LoginScreen.LoginMainScreen.route -> LoginScreen.LoginMainScreen
            ProfileScreen.ProfileMainScreen.route -> ProfileScreen.ProfileMainScreen
            ProfileScreen.ProfileEditScreen.route -> ProfileScreen.ProfileEditScreen
            ExploreScreen.ExploreMainScreen.route -> ExploreScreen.ExploreMainScreen
            else -> LoginScreen.LoginMainScreen
        }
    }

    @Composable
    override fun currentRoute(): String? =
        mNavController.currentBackStackEntryAsState().value?.destination?.route
}