package com.wagnod.navigation.data

import androidx.annotation.StringRes
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.navigation.R

enum class NavSections(
    @StringRes val title: Int,
    val icon: Int,
    val route: String,
    val value: Int,
    val tabNavigationCallback: (Navigator) -> Unit
) {
    DASHBOARD(
        title = R.string.nav_home,
        icon = R.drawable.ic_home,
        route = "dashboard",
        value = 0,
        tabNavigationCallback = { navigator ->
            navigator.navigateToHome()
        }
    ),
    SEARCH(
        title = R.string.nav_explore,
        icon = R.drawable.ic_explore,
        route = "search",
        value = 1,
        tabNavigationCallback = { navigator ->
            navigator.navigateToSearch()
        }),
    NEW(
        title = R.string.nav_entries,
        icon = R.drawable.ic_entries,
        route = "add",
        value = 2,
        tabNavigationCallback = { navigator ->
            navigator.navigateToNew()
        }
    ),
    FRIENDS(
        title = R.string.nav_history,
        icon = R.drawable.ic_history,
        route = "friends",
        value = 3,
        tabNavigationCallback = { navigator ->
            navigator.navigateToFriends()
        }
    ),
    PROFILE(
        title = R.string.nav_profile,
        icon = R.drawable.ic_profile,
        route = "profile",
        value = 4,
        tabNavigationCallback = { navigator ->
            navigator.navigateToProfile()
        }
    )
}