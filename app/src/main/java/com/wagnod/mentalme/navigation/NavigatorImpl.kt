package com.wagnod.mentalme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.wagnod.mentalme.navigation.friends.FriendsNavigator
import com.wagnod.mentalme.navigation.friends.FriendsNavigatorImpl
import com.wagnod.mentalme.navigation.home.HomeNavigator
import com.wagnod.mentalme.navigation.home.HomeNavigatorImp
import com.wagnod.mentalme.navigation.home.HomeNavigatorImp.Companion.rootRoute
import com.wagnod.mentalme.navigation.new.NewNavigator
import com.wagnod.mentalme.navigation.new.NewNavigatorImpl
import com.wagnod.mentalme.navigation.profile.ProfileNavigator
import com.wagnod.mentalme.navigation.profile.ProfileNavigatorImpl
import com.wagnod.mentalme.navigation.search.SearchNavigator
import com.wagnod.mentalme.navigation.search.SearchNavigatorImpl
import com.wagnod.mentalme.ui.data.NavSections

class NavigatorImpl(
    override val homeNavigator: HomeNavigator = HomeNavigatorImp(),
    override val searchNavigator: SearchNavigator = SearchNavigatorImpl(),
    override val newNavigator: NewNavigator = NewNavigatorImpl(),
    override val friendsNavigator: FriendsNavigator = FriendsNavigatorImpl(),
    override val profileNavigator: ProfileNavigator = ProfileNavigatorImpl()
) : Navigator {

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