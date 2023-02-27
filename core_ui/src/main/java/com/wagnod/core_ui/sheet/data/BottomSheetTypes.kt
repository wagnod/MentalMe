package com.wagnod.core_ui.sheet.data

import com.wagnod.core_ui.R

enum class GeneralSheetItems(val title: String, val icon: Int) {
    SUBSCRIPTION("Subscription & Payment", R.drawable.payment),
    SETTINGS("Settings", R.drawable.profile),
    PASSWORD("Password", R.drawable.profile),
    NOTIFICATIONS("Notifications", R.drawable.profile);
}

enum class FeedbackSheetItems(val title: String, val icon: Int) {
    FEEDBACK("App Feedback", R.drawable.feedback),
    REPORT("Bug Report", R.drawable.bug)
}