package com.wagnod.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.wagnod.core_ui.friends.FriendsNavigator
import com.wagnod.core_ui.home.HomeNavigator
import com.wagnod.core_ui.new_screen.NewNavigator
import com.wagnod.core_ui.profile.ProfileNavigator
import com.wagnod.core_ui.search.SearchNavigator
import com.wagnod.navigation.data.NavSections
import com.wagnod.navigation.friends.FriendsNavigatorImpl
import com.wagnod.navigation.home.HomeNavigatorImp
import com.wagnod.navigation.home.HomeNavigatorImp.Companion.rootRoute
import com.wagnod.navigation.new.NewNavigatorImpl
import com.wagnod.navigation.profile.ProfileNavigatorImpl
import com.wagnod.navigation.search.SearchNavigatorImpl

class NavigatorImpl(
    override val homeNavigator: HomeNavigator = HomeNavigatorImp(),
    override val searchNavigator: SearchNavigator = SearchNavigatorImpl(),
    override val newNavigator: NewNavigator = NewNavigatorImpl(),
    override val friendsNavigator: FriendsNavigator = FriendsNavigatorImpl(),
    override val profileNavigator: ProfileNavigator = ProfileNavigatorImpl()
) : com.wagnod.core_ui.Navigator {

    private lateinit var mNavController: NavHostController

    override fun setNavController(navController: NavHostController) {
        mNavController = navController
        homeNavigator.setNavController(navController)
        searchNavigator.setNavController(navController)
        newNavigator.setNavController(navController)
        friendsNavigator.setNavController(navController)
        profileNavigator.setNavController(navController)
    }

    @Composable
    override fun buildNavHost() {
        NavHost(
            navController = mNavController,
            startDestination = rootRoute
        ) {
            homeNavigator.setGraph(this, this@NavigatorImpl)
            searchNavigator.setGraph(this, this@NavigatorImpl)
            newNavigator.setGraph(this, this@NavigatorImpl)
            friendsNavigator.setGraph(this, this@NavigatorImpl)
            profileNavigator.setGraph(this, this@NavigatorImpl)
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