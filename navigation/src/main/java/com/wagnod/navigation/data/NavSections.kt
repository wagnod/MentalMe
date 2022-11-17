package com.wagnod.navigation.data

import androidx.annotation.StringRes
import com.wagnod.core_ui.Navigator
import com.wagnod.navigation.R

enum class NavSections(
    @StringRes val title: Int,
    val icon: Int,
    val route: String,
    val value: Int,
    val tabNavigationCallback: (com.wagnod.core_ui.Navigator) -> Unit
) {
    HOME(
        title = R.string.nav_home,
        icon = R.drawable.ic_home,
        route = "home",
        value = 0,
        tabNavigationCallback = { navigator ->
            navigator.navigateToHome()
        }
    ),
    SEARCH(
        title = R.string.nav_search,
        icon = R.drawable.ic_search,
        route = "search",
        value = 1,
        tabNavigationCallback = { navigator ->
            navigator.navigateToSearch()
        }),
    NEW(
        title = R.string.nav_add,
        icon = R.drawable.ic_add,
        route = "add",
        value = 2,
        tabNavigationCallback = { navigator ->
            navigator.navigateToNew()
        }
    ),
    FRIENDS(
        title = R.string.nav_friends,
        icon = R.drawable.ic_group,
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