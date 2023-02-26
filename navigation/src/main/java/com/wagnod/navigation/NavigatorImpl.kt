package com.wagnod.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.navigators.FriendsNavigator
import com.wagnod.core_ui.navigators.DashboardNavigator
import com.wagnod.core_ui.navigators.LoginNavigator
import com.wagnod.core_ui.navigators.NewNavigator
import com.wagnod.core_ui.navigators.ProfileNavigator
import com.wagnod.core_ui.navigators.SearchNavigator
import com.wagnod.navigation.data.LoginRoute
import com.wagnod.navigation.data.NavSections
import com.wagnod.navigation.login.LoginNavigatorImpl.Companion.rootRoute

class NavigatorImpl(
    override val dashboardNavigator: DashboardNavigator,
    override val searchNavigator: SearchNavigator,
    override val newNavigator: NewNavigator,
    override val friendsNavigator: FriendsNavigator,
    override val profileNavigator: ProfileNavigator,
    override val loginNavigator: LoginNavigator
) : Navigator {

    private lateinit var mNavController: NavHostController

    private val navigators = listOf(
        dashboardNavigator, searchNavigator, newNavigator, friendsNavigator, profileNavigator, loginNavigator
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

    @Composable
    override fun buildNavHost() {
        NavHost(
            navController = mNavController,
            startDestination = rootRoute
        ) {
            navigators.forEach {
                it.setGraph(this, this@NavigatorImpl)
            }
        }
    }

    override fun navigateToHome() {
        mNavController.navigate(NavSections.DASHBOARD.route) {
            popUpTo(NavSections.DASHBOARD.route) {
                inclusive = true
            }
        }
    }

    override fun navigateToSearch() {
        mNavController.navigate(NavSections.SEARCH.route)
    }

    override fun navigateToNew() {
        mNavController.navigate(NavSections.NEW.route)
    }

    override fun navigateToFriends() {
        mNavController.navigate(NavSections.FRIENDS.route)
    }

    override fun navigateToProfile() {
        mNavController.navigate(NavSections.PROFILE.route)
    }

    override fun navigateToLogin() {
        mNavController.navigate(LoginRoute.route)
    }

    override fun navigateToHomeAndClear() {
        mNavController.navigate(NavSections.DASHBOARD.route) {
            popUpTo(LoginRoute.route) {
                inclusive = true
            }
        }
    }

    @Composable
    override fun checkDestination() : Boolean {
        val entry = mNavController.currentBackStackEntryAsState()
        return when (entry.value?.destination?.route) {
            NavSections.DASHBOARD.route,
            NavSections.SEARCH.route,
            NavSections.NEW.route,
            NavSections.FRIENDS.route,
            NavSections.PROFILE.route -> true
            else -> false
        }
    }

    @Composable
    override fun currentRoute() : String? =
        mNavController.currentBackStackEntryAsState().value?.destination?.route
}