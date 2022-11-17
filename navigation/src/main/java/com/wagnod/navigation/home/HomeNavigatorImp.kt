package com.wagnod.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wagnod.home.HomeScreen
import com.wagnod.home.diary.Diary
import com.wagnod.home.goals.Goals
import com.wagnod.home.tracker.MoodTracker
import com.wagnod.core_ui.Navigator
import com.wagnod.core_ui.home.HomeNavigator
import com.wagnod.navigation.data.NavSections

class HomeNavigatorImp : HomeNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: com.wagnod.core_ui.Navigator) {
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