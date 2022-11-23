package com.wagnod.login.ui

import com.wagnod.core_ui.base.BaseViewModel
import com.wagnod.login.ui.AuthContract.*
import com.wagnod.login.ui.sign_in.TextFieldType

class AuthViewModel: BaseViewModel<Event, State, Effect>() {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.OnDataChanged -> changeData(event.type, event.text)
        }
    }

    private fun changeData(type: TextFieldType, text: String) = setState {
        when (type) {
            TextFieldType.EMAIL -> copy(email = text)
            TextFieldType.PASSWORD -> copy(password = text)
        }
    }
}