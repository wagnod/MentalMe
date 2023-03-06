package com.wagnod.core_ui.navigators

import com.wagnod.core.datastore.user.UserInfo
import com.wagnod.core_ui.navigators.module.ModuleNavigator

interface ProfileNavigator : ModuleNavigator {
    fun navigateToEditScreen(user: UserInfo)
}