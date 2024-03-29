package com.wagnod.login.ui.auth

import com.wagnod.core_ui.view_model.BaseViewModel
import com.wagnod.domain.app.InitAppDataUseCase
import com.wagnod.domain.execute
import com.wagnod.domain.login.repository.AuthData
import com.wagnod.domain.login.usecase.CheckIsUserAuthorizedUseCase
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
    private val signInUseCase: SignInUseCase,
    private val checkIsUserAuthorizedUseCase: CheckIsUserAuthorizedUseCase,
    private val initAppDataUseCase: InitAppDataUseCase
) : BaseViewModel<Event, State, Effect>() {

    init {
        if (checkIsUserAuthorizedUseCase.isUserAuthorized()) {
            io {
                initAppDataUseCase.execute()
            }
            setEffect { Effect.NavigateToHome }
        }
    }

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.OnDataChanged -> changeData(event.type, event.text)
            is Event.OnScreenChanged -> toggleScreenType()
            is Event.OnShowHidePasswordChanged -> changePasswordVisibility()
            is Event.OnAuthClick -> auth()
            is Event.CheckIsUserAuthorized -> isUserAuthorised()
        }
    }

    private fun auth() {
        setState { copy(buttonEnabled = false) }
        io {
            runCatching {
                val data = AuthData(
                    email = viewState.value.email,
                    password = viewState.value.password,
                    name = viewState.value.name
                )
                when (viewState.value.screenType) {
                    SIGN_IN -> signIn(data)
                    SIGN_UP -> signUp(data)
                }
            }.onSuccess {
                initAppDataUseCase.execute()
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
        if (it.id.isNotEmpty()) setEffect { Effect.NavigateToHome }
    }.onFailure {
        hideProgress()
    }

    private fun isUserAuthorised() = launch {
        runCatching {
            checkIsUserAuthorizedUseCase.execute()
        }.onSuccess {
            if (it) {
                setEffect { Effect.NavigateToHome }
            } else {
                setEffect { Effect.NavigateToLoginScreen }
            }
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