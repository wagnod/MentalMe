package com.wagnod.core_ui.sheet.data

import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.sheet.data.SheetItem.*
import com.wagnod.data.AppDispatchers

class BottomSheetItemsHelper(
    private val navigator: Navigator,
) {
    private val subscriptionPayment = SubscriptionPayment() to { navigator.navigateToHome() }
    private val profileSettings =
        ProfileSettings() to { navigator.profileNavigator.navigateToEditScreen() }
    private val password = Password() to { navigator.navigateToProfile() }
    private val notifications = Notifications() to { navigator.navigateToProfile() }

    fun getGeneralSheetItems() = listOf(
        subscriptionPayment,
        profileSettings,
        password,
        notifications
    )

    private val bugReport = BugReport() to navigator.navigateToHome()
    private val appFeedback = Feedback() to navigator.navigateToHome()

    fun getFeedbackSheetItems() = listOf(
        appFeedback,
        bugReport
    )
}