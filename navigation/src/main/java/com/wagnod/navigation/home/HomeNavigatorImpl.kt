package com.wagnod.navigation.home

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.navigators.HomeNavigator
import com.wagnod.dashboard.ui.DashboardMainScreen
import com.wagnod.home.HomeScreen
import com.wagnod.home.diary.Diary
import com.wagnod.home.goals.GoalCreator
import com.wagnod.home.goals.GoalsScreen
import com.wagnod.home.tracker.MoodTracker
import com.wagnod.navigation.data.Keys
import com.wagnod.navigation.data.NavSections

class HomeNavigatorImpl : HomeNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
        navGraphBuilder.navigation(NavSections.HOME.route, rootRoute) {
            composable(NavSections.HOME.route) { DashboardMainScreen(navigator) }
            composable(HomeNavRoutes.TRACKER.route) { MoodTracker() }
            composable(HomeNavRoutes.DIARY.route) { Diary() }
            composable(HomeNavRoutes.GOALS.route) { GoalsScreen(navigator) }
            composable(
                route = HomeNavRoutes.CREATE_GOAL.route + "/{${Keys.goalIndexKey}}",
                arguments = listOf(navArgument(Keys.goalIndexKey) {
                    type = NavType.IntType
                })
            ) { backStackEntry ->
                GoalCreator(navigator, backStackEntry.arguments?.getInt(Keys.goalIndexKey))
            }
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

    override fun navigateToGoalCreator(goalIndex: Int) {
        mNavController.navigate(HomeNavRoutes.CREATE_GOAL.route + "/${goalIndex}")
    }


    companion object {
        const val rootRoute = "home_root"
    }

}