package com.wagnod.login.ui.auth

import androidx.lifecycle.viewModelScope
import com.wagnod.core_ui.base.BaseViewModel
import com.wagnod.domain.login.repository.AuthData
import com.wagnod.domain.login.usecase.SignInUseCase
import com.wagnod.domain.login.usecase.SignUpUseCase
import com.wagnod.login.ui.auth.AuthContract.*
import com.wagnod.login.ui.auth.data.ScreenType
import com.wagnod.login.ui.auth.data.ScreenType.*
import com.wagnod.login.ui.auth.data.TextFieldType
import com.wagnod.login.ui.auth.data.TextFieldType.*
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase
): BaseViewModel<Event, State, Effect>() {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.OnDataChanged -> changeData(event.type, event.text)
            is Event.OnScreenChanged -> changeScreen(event.screenType)
            is Event.OnShowHidePasswordChanged -> changePasswordVisibility()
            is Event.OnAuthClick -> auth()
        }
    }

    private fun auth() = launch {
        val data = AuthData(viewState.value.email, viewState.value.password)
        when (viewState.value.screenType) {
            LOGIN -> signInUseCase.execute(data)
            SIGNUP -> signUpUseCase.execute(data)
        }
    }

    private fun changeData(type: TextFieldType, text: String) = setState {
        when (type) {
            NAME -> copy(name = text)
            EMAIL -> copy(email = text)
            PASSWORD -> copy(password = text)
            CONFIRM -> copy(passwordRepeat = text)
        }
    }

    private fun changeScreen(type: ScreenType) = setState {
        when (type) {
            LOGIN -> copy(screenType = LOGIN)
            SIGNUP -> copy(screenType = SIGNUP)
        }
    }

    private fun changePasswordVisibility() = setState {
        copy(showPassword = !showPassword)
    }
}