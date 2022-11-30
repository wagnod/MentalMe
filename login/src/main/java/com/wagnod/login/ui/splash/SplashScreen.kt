package com.wagnod.login.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.wagnod.core_ui.Navigator
import com.wagnod.core_ui.theme.MentalMeTheme
import com.wagnod.login.R
import com.wagnod.login.ui.auth.AuthContract
import com.wagnod.login.ui.auth.AuthContract.Effect
import com.wagnod.login.ui.auth.AuthViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SplashScreen(
    navigator: Navigator,
    viewModel: AuthViewModel = getViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.setEvent(AuthContract.Event.CheckIsUserAuthorized)
    }

    LaunchedEffect(true) {
        viewModel.effect.collect { value ->
            when (value) {
                Effect.NavigateToHome -> navigator.navigateToHomeAndClear()
                Effect.NavigateToLoginScreen -> navigator.navigateToLogin()
            }
        }
    }

    SplashScreenContent()
}

@Composable
fun SplashScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.chaos),
            contentDescription = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplash() {
    MentalMeTheme {
        SplashScreenContent()
    }
}