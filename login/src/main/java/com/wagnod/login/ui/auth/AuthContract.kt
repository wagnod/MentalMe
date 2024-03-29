package com.wagnod.login.ui.auth

import com.wagnod.core_ui.view_model.ViewEvent
import com.wagnod.core_ui.view_model.ViewSideEffect
import com.wagnod.core_ui.view_model.ViewState
import com.wagnod.login.ui.auth.data.ScreenType
import com.wagnod.login.ui.auth.data.ScreenType.*
import com.wagnod.login.ui.auth.data.TextFieldType

interface AuthContract {

    interface Event : ViewEvent {
        data class OnDataChanged(val type: TextFieldType, val text: String) : Event
        object OnScreenChanged : Event
        object OnShowHidePasswordChanged : Event
        object OnAuthClick : Event
        object CheckIsUserAuthorized : Event
    }

    data class State(
        val screenType: ScreenType = SIGN_IN,
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val passwordRepeat: String = "",
        val showPassword: Boolean = false,
        val buttonEnabled: Boolean = true
    ) : ViewState {

        fun getFieldByType(type: TextFieldType): String {
            return when (type) {
                TextFieldType.NAME -> name
                TextFieldType.EMAIL -> email
                TextFieldType.PASSWORD -> password
                TextFieldType.CONFIRM -> passwordRepeat
            }
        }
    }

    sealed interface Effect : ViewSideEffect {
        object NavigateToHome : Effect
        object NavigateToLoginScreen : Effect
    }

    interface Listener {
        fun onDataChanged(type: TextFieldType, text: String)
        fun onScreenChanged()
        fun onShowHidePasswordChanged()
        fun onAuthClick()
    }
}