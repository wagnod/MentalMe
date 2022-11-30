package com.wagnod.login.ui.auth

import com.wagnod.core_ui.base.BaseViewModel
import com.wagnod.domain.login.repository.AuthData
import com.wagnod.domain.login.usecase.SignInUseCase
import com.wagnod.domain.login.usecase.SignUpUseCase
import com.wagnod.login.ui.auth.AuthContract.*
import com.wagnod.login.ui.auth.data.ScreenType.SIGN_IN
import com.wagnod.login.ui.auth.data.ScreenType.SIGN_UP
import com.wagnod.login.ui.auth.data.TextFieldType
import com.wagnod.login.ui.auth.data.TextFieldType.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase
) : BaseViewModel<Event, State, Effect>() {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.OnDataChanged -> changeData(event.type, event.text)
            is Event.OnScreenChanged -> toggleScreenType()
            is Event.OnShowHidePasswordChanged -> changePasswordVisibility()
            is Event.OnAuthClick -> auth()
        }
    }

    private fun auth() {
        setState { copy(buttonEnabled = false) }
        launch {
            val data = AuthData(viewState.value.email, viewState.value.password)
            when (viewState.value.screenType) {
                SIGN_IN -> signIn(data)
                SIGN_UP -> signUp(data)
            }
        }
    }

    private suspend fun hideProgress() {
        delay(500)
        setState { copy(buttonEnabled = true) }
    }

    private suspend fun signIn(authData: AuthData) = runCatching {
        signInUseCase.execute(authData)
    }.onSuccess {
        hideProgress()
        if (it) setEffect { Effect.NavigateToHome }
    }.onFailure {
        hideProgress()
    }

    private suspend fun signUp(authData: AuthData) = runCatching {
        signUpUseCase.execute(authData)
    }.onSuccess {
        hideProgress()
        if (it) setEffect { Effect.NavigateToHome }
    }.onFailure {
        hideProgress()
    }

    private fun changeData(type: TextFieldType, text: String) = setState {
        when (type) {
            NAME -> copy(name = text)
            EMAIL -> copy(email = text)
            PASSWORD -> copy(password = text)
            CONFIRM -> copy(passwordRepeat = text)
        }
    }

    private fun toggleScreenType() = setState {
        when (viewState.value.screenType) {
            SIGN_IN -> copy(screenType = SIGN_UP)
            SIGN_UP -> copy(screenType = SIGN_IN)
        }
    }

    private fun changePasswordVisibility() = setState {
        copy(showPassword = !showPassword)
    }
}