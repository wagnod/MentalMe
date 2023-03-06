package com.wagnod.core_ui.navigators

import com.wagnod.core.datastore.articles.Article
import com.wagnod.core_ui.navigators.module.ModuleNavigator

interface DashboardNavigator : ModuleNavigator {
    fun navigateToArticle(article: Article)
}