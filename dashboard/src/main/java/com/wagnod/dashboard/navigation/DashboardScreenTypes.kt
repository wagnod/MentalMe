package com.wagnod.dashboard.navigation

import com.wagnod.core_ui.Keys

enum class DashboardScreenTypes(val route: String) {
    DASHBOARD_MAIN_SCREEN("dashboard_main_screen"),
    ARTICLE("articles?article={${Keys.ARG_ARTICLE}}"),
}