package com.wagnod.core_ui.navigators

import com.wagnod.core_ui.navigators.module.ModuleNavigator

interface HomeNavigator : ModuleNavigator {
    fun navigateToGoals()
    fun navigateToDiary()
    fun navigateToTracker()
    fun navigateToGoalCreator(goalIndex: Int)
}