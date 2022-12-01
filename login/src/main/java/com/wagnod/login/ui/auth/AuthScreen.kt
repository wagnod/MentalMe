package com.wagnod.login.ui.auth

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.wagnod.core_ui.Navigator
import com.wagnod.login.R
import com.wagnod.login.ui.auth.AuthContract.*
import com.wagnod.login.ui.auth.AuthContract.Effect.NavigateToHome
import com.wagnod.login.ui.auth.AuthContract.Effect.NavigateToLoginScreen
import com.wagnod.login.ui.auth.data.AuthScreenViewsData
import com.wagnod.login.ui.auth.data.ScreenType
import com.wagnod.login.ui.auth.data.ScreenType.SIGN_IN
import com.wagnod.login.ui.auth.data.ScreenType.SIGN_UP
import com.wagnod.login.ui.auth.data.TextFieldType
import com.wagnod.login.ui.auth.data.TextFieldType.*
import org.koin.androidx.compose.getViewModel

@Composable
fun AuthScreen(
    navigator: Navigator,
    viewModel: AuthViewModel = getViewModel()
) {
    val listener = object : Listener {
        override fun onDataChanged(type: TextFieldType, text: String) {
            viewModel.setEvent(Event.OnDataChanged(type, text))
        }

        override fun onScreenChanged() {
            viewModel.setEvent(Event.OnScreenChanged)
        }

        override fun onShowHidePasswordChanged() {
            viewModel.setEvent(Event.OnShowHidePasswordChanged)
        }

        override fun onAuthClick() {
            viewModel.setEvent(Event.OnAuthClick)
        }
    }

    val state = viewModel.viewState.value

    BackHandler {
        when (state.screenType) {
            SIGN_IN -> navigator.back()
            SIGN_UP -> listener.onScreenChanged()
        }
    }

    LaunchedEffect(true) {
        viewModel.effect.collect { value ->
            when (value) {
                NavigateToHome -> navigator.navigateToHomeAndClear()
                NavigateToLoginScreen -> navigator.navigateToLogin()
            }
        }
    }

    AuthScreenContent(state, listener)
}

@Composable
fun AuthScreenContent(
    state: State,
    listener: Listener?
) = ConstraintLayout(
    modifier = Modifier.fillMaxSize()
) {
    val (inputs, login) = createRefs()

    Column(
        modifier = Modifier.constrainAs(inputs) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(state)
        TextFields(state = state, listener = listener, type = state.screenType)
        SignUpButton(state, listener)
    }

    val textModifier = Modifier.constrainAs(login) {
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
    BottomText(state = state, modifier = textModifier, listener = listener)
}

@Composable
private fun Title(
    state: State
) {
    Text(
        modifier = Modifier.padding(vertical = 10.dp),
        text = when (state.screenType) {
            SIGN_UP -> "Sign Up"
            SIGN_IN -> "Login"
        },
        style = TextStyle(fontSize = 30.sp)
    )
}

@Composable
private fun TextFields(
    type: ScreenType,
    state: State,
    listener: Listener?,
) {
    LazyColumn {
        val list = AuthScreenViewsData.getAuthScreenViewsData()
        val itemsList = when (type) {
            SIGN_UP -> list
            SIGN_IN -> list.slice(1..2)
        }
        items(itemsList) { item ->
            InputView(state = state, listener = listener, data = item)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun InputView(
    state: State,
    listener: Listener?,
    data: AuthScreenViewsData
) {
    val imeAction = when (data.type) {
        PASSWORD -> if (state.screenType == SIGN_UP) ImeAction.Next else ImeAction.Done
        CONFIRM -> ImeAction.Done
        else -> ImeAction.Next
    }

    val keyboardType = when (data.type) {
        NAME -> KeyboardType.Text
        EMAIL -> KeyboardType.Email
        PASSWORD, CONFIRM -> KeyboardType.Password
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .padding(vertical = 10.dp),
        value = state.getFieldByType(data.type),
        onValueChange = {
            listener?.onDataChanged(data.type, it)
        },
        label = {
            Text(text = data.label)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.2f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        leadingIcon = {
            Icon(
                painterResource(id = data.icon),
                modifier = Modifier.size(24.dp),
                contentDescription = ""
            )
        },
        visualTransformation = when (data.type) {
            CONFIRM, PASSWORD -> {
                if (state.showPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }
            }
            else -> VisualTransformation.None
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                listener?.onAuthClick()
            }
        ),
        trailingIcon = {
            if (data.type == CONFIRM || data.type ==PASSWORD ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            listener?.onShowHidePasswordChanged()
                        }
                ) {
                    Icon(
                        contentDescription = "",
                        painter = painterResource(
                            id = if (state.showPassword) R.drawable.hide_password else R.drawable.ic_show_password
                        ),
                        modifier = Modifier
                            .padding(6.dp)
                            .size(24.dp)
                    )
                }
            }
        }
    )
}

@Composable
private fun SignUpButton(
    state: State,
    listener: Listener?
) {
    Button(
        onClick = {
            listener?.onAuthClick()
        },
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            disabledBackgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.4f)
        ),
        modifier = Modifier
            .padding(horizontal = 50.dp, vertical = 10.dp)
            .fillMaxWidth()
            .height(50.dp),
        enabled = state.buttonEnabled
    )
    {
        if (state.buttonEnabled) {
            Text(
                text = if (state.screenType == SIGN_UP) "Sign Up" else "Login"
            )
        } else {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                strokeWidth = 3.dp
            )
        }
    }
}

@Composable
private fun BottomText(
    state: State,
    modifier: Modifier,
    listener: Listener?
) {
    Row(
        modifier = modifier
            .padding(bottom = 32.dp)
    ) {
        Text(
            text = when (state.screenType) {
                SIGN_UP -> "Already have an account? "
                SIGN_IN -> "Don't have an account? "
            },
            style = TextStyle(fontSize = 14.sp)
        )
        ClickableText(
            text = AnnotatedString(
                when (state.screenType) {
                    SIGN_UP -> "Sign in here"
                    SIGN_IN -> "Sign up here"
                }
            ),
            onClick = {
                listener?.onScreenChanged()
            },
            style = TextStyle(
                fontSize = 14.sp,
                color = MaterialTheme.colors.primary,
                textDecoration = TextDecoration.Underline
            )
        )
    }
}

