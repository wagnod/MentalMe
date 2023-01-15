package com.wagnod.core_ui.navigators.module

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.wagnod.core_ui.navigators.main.Navigator

interface ModuleNavigator {
    fun setNavController(navController: NavController)
    fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator)
}