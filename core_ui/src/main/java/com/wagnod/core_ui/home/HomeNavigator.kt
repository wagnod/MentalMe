package com.wagnod.core_ui.home

import com.wagnod.core_ui.ModuleNavigator

interface HomeNavigator : ModuleNavigator {
    fun navigateToGoals()
    fun navigateToDiary()
    fun navigateToTracker()
    fun navigateToGoalCreator()
}