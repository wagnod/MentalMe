package com.wagnod.navigation.home

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.navigators.DashboardNavigator
import com.wagnod.dashboard.ui.DashboardMainScreen
import com.wagnod.home.diary.Diary
import com.wagnod.home.goals.GoalCreator
import com.wagnod.home.goals.GoalsScreen
import com.wagnod.home.tracker.MoodTracker
import com.wagnod.navigation.data.Keys
import com.wagnod.navigation.data.NavSections

class DashboardNavigatorImpl : DashboardNavigator {

    private lateinit var mNavController: NavController

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
        navGraphBuilder.navigation(NavSections.DASHBOARD.route, rootRoute) {
            composable(NavSections.DASHBOARD.route) { DashboardMainScreen(navigator) }
            composable(DashboardNavRoutes.TRACKER.route) { MoodTracker() }
            composable(DashboardNavRoutes.DIARY.route) { Diary() }
            composable(DashboardNavRoutes.GOALS.route) { GoalsScreen(navigator) }
            composable(
                route = DashboardNavRoutes.CREATE_GOAL.route + "/{${Keys.goalIndexKey}}",
                arguments = listOf(navArgument(Keys.goalIndexKey) {
                    type = NavType.IntType
                })
            ) { backStackEntry ->
                GoalCreator(navigator, backStackEntry.arguments?.getInt(Keys.goalIndexKey))
            }
        }
    }

    override fun navigateToGoals() {
        mNavController.navigate(DashboardNavRoutes.GOALS.route)
    }

    override fun navigateToDiary() {
        mNavController.navigate(DashboardNavRoutes.DIARY.route)
    }

    override fun navigateToTracker() {
        mNavController.navigate(DashboardNavRoutes.TRACKER.route)
    }

    override fun navigateToGoalCreator(goalIndex: Int) {
        mNavController.navigate(DashboardNavRoutes.CREATE_GOAL.route + "/${goalIndex}")
    }


    companion object {
        const val rootRoute = "dashboard_root"
    }

}