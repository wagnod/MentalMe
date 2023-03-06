package com.wagnod.dashboard.navigation

import androidx.navigation.*
import com.wagnod.core_ui.navigators.DashboardNavigator
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.dashboard.DashboardScreen.*
import com.wagnod.core.datastore.articles.Article

class DashboardNavigatorImpl : DashboardNavigator {

    private lateinit var mNavController: NavController

    override fun navigateToArticle(article: Article) {
        ArticleMainScreen.navigate(article, mNavController)
    }

    override fun setNavController(navController: NavController) {
        mNavController = navController
    }

    override fun setGraph(navGraphBuilder: NavGraphBuilder, navigator: Navigator) {
        navGraphBuilder.navigation(
            DashboardMainScreen.route,
            DashboardMainScreen.rootRoute
        ) {
            DashboardMainScreen.createScreen(navGraphBuilder, navigator)
            ArticleMainScreen.createScreen(this, navigator)
        }
    }
}