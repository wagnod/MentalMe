package com.wagnod.core_ui.sheet.data

import com.wagnod.core_ui.R

enum class GeneralSheetItems(val title: String, val icon: Int) {
    SUBSCRIPTION("Subscription & Payment", R.drawable.ic_payment),
    SETTINGS("Settings", R.drawable.ic_settings),
    PASSWORD("Password", R.drawable.password),
    NOTIFICATIONS("Notifications", R.drawable.ic_notification);
}

enum class FeedbackSheetItems(val title: String, val icon: Int) {
    FEEDBACK("App Feedback", R.drawable.ic_feedback),
    REPORT("Bug Report", R.drawable.ic_report)
}