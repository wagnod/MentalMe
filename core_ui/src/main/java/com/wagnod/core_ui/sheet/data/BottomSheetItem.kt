package com.wagnod.core_ui.sheet.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.wagnod.core_ui.R

interface BottomSheetItem

sealed class SheetItem(
    @StringRes
    open val title: Int,
    @DrawableRes
    open val icon: Int
) : BottomSheetItem {
    data class SubscriptionPayment(
        override val title: Int = R.string.subscription_payment,
        override val icon: Int = R.drawable.payment
    ) : SheetItem(title, icon)

    data class ProfileSettings(
        override val title: Int = R.string.profile_settings,
        override val icon: Int = R.drawable.profile
    ) : SheetItem(title, icon)

    data class Password(
        override val title: Int = R.string.password,
        override val icon: Int = R.drawable.profile
    ) : SheetItem(title, icon)

    data class Notifications(
        override val title: Int = R.string.notifications,
        override val icon: Int = R.drawable.profile
    ) : SheetItem(title, icon)

    data class Feedback(
        override val title: Int = R.string.feedback,
        override val icon: Int = R.drawable.profile
    ) : SheetItem(title, icon)

    data class BugReport(
        override val title: Int = R.string.bug_report,
        override val icon: Int = R.drawable.profile
    ) : SheetItem(title, icon)
}
