package com.wagnod.login.ui.auth

import com.wagnod.core_ui.base.ViewEvent
import com.wagnod.core_ui.base.ViewSideEffect
import com.wagnod.core_ui.base.ViewState
import com.wagnod.login.ui.auth.data.ScreenType
import com.wagnod.login.ui.auth.data.TextFieldType

interface AuthContract {

    interface Event : ViewEvent {
        data class OnDataChanged(val type: TextFieldType, val text: String) : Event
        data class OnScreenChanged(val screenType: ScreenType) : Event
    }

    data class State(
        val screenType: ScreenType = ScreenType.LOGIN,
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val passwordRepeat: String = ""
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

    sealed interface Effect : ViewSideEffect

    interface Listener {
        fun onDataChanged(type: TextFieldType, text: String)
        fun onScreenChanged(type: ScreenType)
    }
}