package com.wagnod.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.wagnod.core_ui.Navigator
import com.wagnod.core_ui.friends.FriendsNavigator
import com.wagnod.core_ui.home.HomeNavigator
import com.wagnod.core_ui.new_screen.NewNavigator
import com.wagnod.core_ui.profile.ProfileNavigator
import com.wagnod.core_ui.search.SearchNavigator
import com.wagnod.navigation.data.NavSections
import com.wagnod.navigation.home.HomeNavigatorImpl.Companion.rootRoute

class NavigatorImpl(
    override val homeNavigator: HomeNavigator,
    override val searchNavigator: SearchNavigator,
    override val newNavigator: NewNavigator,
    override val friendsNavigator: FriendsNavigator,
    override val profileNavigator: ProfileNavigator
) : Navigator {

    private lateinit var mNavController: NavHostController

    private val navigators = listOf(
        homeNavigator, searchNavigator, newNavigator, friendsNavigator, profileNavigator
    )

    override fun setNavController(navController: NavHostController) {
        mNavController = navController
        navigators.forEach {
            it.setNavController(navController)
        }
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
        mNavController.navigate(NavSections.HOME.route)
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
}