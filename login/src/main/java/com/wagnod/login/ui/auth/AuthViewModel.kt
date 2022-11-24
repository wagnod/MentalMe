package com.wagnod.login.ui.auth

import com.wagnod.core_ui.base.BaseViewModel
import com.wagnod.login.ui.auth.AuthContract.*
import com.wagnod.login.ui.auth.data.ScreenType
import com.wagnod.login.ui.auth.data.TextFieldType

class AuthViewModel: BaseViewModel<Event, State, Effect>() {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.OnDataChanged -> changeData(event.type, event.text)
            is Event.OnScreenChanged -> changeScreen(event.screenType)
            is Event.OnShowHidePasswordChanged -> changePasswordVisibility()
        }
    }

    private fun changeData(type: TextFieldType, text: String) = setState {
        when (type) {
            TextFieldType.NAME -> copy(name = text)
            TextFieldType.EMAIL -> copy(email = text)
            TextFieldType.PASSWORD -> copy(password = text)
            TextFieldType.CONFIRM -> copy(passwordRepeat = text)
        }
    }

    private fun changeScreen(type: ScreenType) = setState {
        when (type) {
            ScreenType.LOGIN -> copy(screenType = ScreenType.LOGIN)
            ScreenType.SIGNUP -> copy(screenType = ScreenType.SIGNUP)
        }
    }

    private fun changePasswordVisibility() = setState {
        copy(showPassword = !showPassword)
    }
}