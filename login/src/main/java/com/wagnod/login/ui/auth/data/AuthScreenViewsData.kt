package com.wagnod.login.ui.auth.data

import com.wagnod.login.R

data class AuthScreenViewsData(
    val icon: Int,
    val label: String,
    val type: TextFieldType
) {
    companion object {
        fun getAuthScreenViewsData() = listOf(
            AuthScreenViewsData(
                icon = R.drawable.ic_person,
                label = "Name",
                type = TextFieldType.NAME
            ),
            AuthScreenViewsData(
                icon = R.drawable.ic_email,
                label = "Email",
                type = TextFieldType.EMAIL
            ),
            AuthScreenViewsData(
                icon = R.drawable.ic_password,
                label = "Password",
                type = TextFieldType.PASSWORD
            ),
            AuthScreenViewsData(
                icon = R.drawable.ic_password,
                label = "Confirm password",
                type = TextFieldType.CONFIRM
            )
        )
    }
}
