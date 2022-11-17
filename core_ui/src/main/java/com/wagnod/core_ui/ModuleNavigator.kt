package com.wagnod.core_ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface ModuleNavigator {
    fun setNavController(navController: NavController)
    fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator)
}