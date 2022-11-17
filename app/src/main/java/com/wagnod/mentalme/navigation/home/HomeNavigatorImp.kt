package com.wagnod.mentalme.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wagnod.mentalme.navigation.Navigator
import com.wagnod.mentalme.ui.home.HomeScreen
import com.wagnod.mentalme.ui.data.NavSections
import com.wagnod.mentalme.ui.home.diary.Diary
import com.wagnod.mentalme.ui.home.goals.Goals
import com.wagnod.mentalme.ui.home.tracker.MoodTracker

class HomeNavigatorImp : HomeNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
        navGraphBuilder.navigation(NavSections.HOME.route, rootRoute) {
            composable(NavSections.HOME.route) { HomeScreen(navigator) }
            composable(HomeNavRoutes.TRACKER.route) { MoodTracker() }
            composable(HomeNavRoutes.DIARY.route) { Diary() }
            composable(HomeNavRoutes.GOALS.route) { Goals() }
        }
    }

    override fun navigateToGoals() {
        mNavController.navigate(HomeNavRoutes.GOALS.route)
    }

    override fun navigateToDiary() {
        mNavController.navigate(HomeNavRoutes.DIARY.route)
    }

    override fun navigateToTracker() {
        mNavController.navigate(HomeNavRoutes.TRACKER.route)
    }


    companion object {
        const val rootRoute = "home_root"
    }

}