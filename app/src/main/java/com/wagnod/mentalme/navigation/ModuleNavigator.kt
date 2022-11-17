package com.wagnod.mentalme.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface ModuleNavigator {
    fun setNavController(navController: NavController)
    fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator)
}