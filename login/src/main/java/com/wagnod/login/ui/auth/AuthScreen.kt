package com.wagnod.login.ui.auth

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.wagnod.core_ui.Navigator
import com.wagnod.login.ui.auth.AuthContract.*
import com.wagnod.login.ui.auth.data.AuthScreenViewsData
import com.wagnod.login.ui.auth.data.ScreenType
import com.wagnod.login.ui.auth.data.TextFieldType
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
        override fun onScreenChanged(type: ScreenType) {
            viewModel.setEvent(Event.OnScreenChanged(type))
        }
    }

    val state = viewModel.viewState.value

    BackHandler {
        when(state.screenType) {
            ScreenType.LOGIN -> navigator.back()
            ScreenType.SIGNUP -> listener.onScreenChanged(ScreenType.LOGIN)
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
            ScreenType.SIGNUP -> "Sign Up"
            ScreenType.LOGIN -> "Login"
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
        when (type) {
            ScreenType.LOGIN -> {
                items(list.slice(1..2)) { item ->
                    InputView(state = state, listener = listener, data = item)
                }
            }
            ScreenType.SIGNUP -> {
                items(list) { item ->
                    InputView(state = state, listener = listener, data = item)
                }
            }
        }
    }
}

@Composable
private fun InputView(
    state: State,
    listener: Listener?,
    data: AuthScreenViewsData
) {
    TextField(
        modifier = Modifier.padding(vertical = 10.dp),
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
        leadingIcon = {
            Icon(
                painterResource(id = data.icon),
                modifier = Modifier.size(24.dp),
                contentDescription = ""
            )
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
//            accountService.linkAccount(
//                userLoginData.email,
//                userLoginData.password
//            ) {
//                error ->
//                if (error == null) {
//                    navigator?.navigateToHome()
//                } else {
//                    error(error)
//                }
//            }
        },
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier
            .padding(horizontal = 50.dp, vertical = 10.dp)
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        )
    )
    {
        Text(
            text = when (state.screenType) {
                ScreenType.SIGNUP -> "Sign Up"
                ScreenType.LOGIN -> "Login"
            }
        )
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
                ScreenType.SIGNUP -> "Already have an account? "
                ScreenType.LOGIN -> "Don't have an account? "
            },
            style = TextStyle(fontSize = 14.sp)
        )
        ClickableText(
            text = AnnotatedString(
                when (state.screenType) {
                    ScreenType.SIGNUP -> "Sign in here"
                    ScreenType.LOGIN -> "Sign up here"
                }
            ),
            onClick = {
                when (state.screenType) {
                    ScreenType.SIGNUP -> listener?.onScreenChanged(ScreenType.LOGIN)
                    ScreenType.LOGIN -> listener?.onScreenChanged(ScreenType.SIGNUP)
                }

            },
            style = TextStyle(
                fontSize = 14.sp,
                color = MaterialTheme.colors.primary,
                textDecoration = TextDecoration.Underline
            )
        )
    }
}

