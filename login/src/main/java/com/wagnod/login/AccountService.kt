package com.wagnod.login

import com.wagnod.core_ui.Navigator

interface AccountService {
    fun authenticate(email: String, password: String, navigator: Navigator?)
    fun linkAccount(email: String, password: String, navigator: Navigator?)
}