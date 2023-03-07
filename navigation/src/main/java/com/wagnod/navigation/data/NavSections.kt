package com.wagnod.navigation.data

import androidx.annotation.StringRes
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.dashboard.DashboardScreen
import com.wagnod.entries.EntriesScreen
import com.wagnod.history.HistoryScreen
import com.wagnod.navigation.R
import com.wagnod.profile.ProfileScreen
import com.wagnod.explore.ExploreScreen

enum class NavSections(
    @StringRes val title: Int,
    val icon: Int,
    val route: String,
    val tabNavigationCallback: (Navigator) -> Unit
) {
    DASHBOARD(
        title = R.string.nav_home,
        icon = R.drawable.ic_home,
        route = DashboardScreen.DashboardMainScreen.route,
        tabNavigationCallback = { navigator ->
            navigator.navigateToHome()
        }
    ),
    SEARCH(
        title = R.string.nav_explore,
        icon = R.drawable.ic_explore,
        route = ExploreScreen.ExploreMainScreen.route,
        tabNavigationCallback = { navigator ->
            navigator.navigateToSearch()
        }),
    NEW(
        title = R.string.nav_entries,
        icon = R.drawable.ic_entries,
        route = EntriesScreen.EntriesMainScreen.route,
        tabNavigationCallback = { navigator ->
            navigator.navigateToEntries()
        }
    ),
    FRIENDS(
        title = R.string.nav_history,
        icon = R.drawable.ic_history,
        route = HistoryScreen.HistoryMainScreen.route,
        tabNavigationCallback = { navigator ->
            navigator.navigateToFriends()
        }
    ),
    PROFILE(
        title = R.string.nav_profile,
        icon = R.drawable.ic_profile,
        route = ProfileScreen.ProfileMainScreen.route,
        tabNavigationCallback = { navigator ->
            navigator.navigateToProfile()
        }
    )
}