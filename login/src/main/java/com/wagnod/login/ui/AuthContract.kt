package com.wagnod.login.ui

import com.wagnod.core_ui.base.ViewEvent
import com.wagnod.core_ui.base.ViewSideEffect
import com.wagnod.core_ui.base.ViewState
import com.wagnod.login.ui.sign_in.TextFieldType

interface AuthContract {

    interface Event: ViewEvent {
        data class OnDataChanged(val type: TextFieldType, val text: String): Event
    }

    data class State(
        val email: String = "",
        val password: String = ""
    ): ViewState

    sealed interface Effect: ViewSideEffect {

    }

    interface Listener {
        fun toSignUp()
        fun onDataChanged(type: TextFieldType, text: String)
    }
}