package com.wagnod.mentalme.navigation.home

import com.wagnod.mentalme.navigation.ModuleNavigator

interface HomeNavigator : ModuleNavigator {
    fun navigateToGoals()
    fun navigateToDiary()
    fun navigateToTracker()
}