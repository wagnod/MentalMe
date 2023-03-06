package com.wagnod.profile.navigation

import com.wagnod.core_ui.Keys

enum class ProfileScreenTypes(val route: String) {
    PROFILE_MAIN_SCREEN("profile_main_screen"),
    PROFILE_EDIT_SCREEN("profile_edit_screen?user={${Keys.ARG_USER}}")
}