package com.wagnod.mentalme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.wagnod.core_ui.Navigator
import com.wagnod.core_ui.theme.MentalMeTheme
import com.wagnod.login.ui.auth.AuthContract
import com.wagnod.login.ui.auth.AuthViewModel
import com.wagnod.navigation.data.NavSections
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val navigator by inject<Navigator>()
    private val viewModel by viewModel<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MentalMeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldMentalMe(navigator)
                }
            }
            LaunchedEffect(true) {
                viewModel.effect.collect { value ->
                    when (value) {
                        AuthContract.Effect.NavigateToHome -> navigator.navigateToHomeAndClear()
                        AuthContract.Effect.NavigateToLoginScreen -> navigator.navigateToLogin()
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(navigator: Navigator) {
    val currentRoute = navigator.currentRoute()
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        NavSections.values().forEach { section ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(section.icon),
                        "home navigation button",
                        modifier = Modifier.size(30.dp)
                    )
                }, label = { Text(stringResource(section.title)) },
                alwaysShowLabel = false,
                selected = (currentRoute == section.route),
                onClick = {
                    section.tabNavigationCallback(navigator)
                }
            )
        }
    }
}

@Composable
fun ScaffoldMentalMe(navigator: Navigator) {
    val navController = rememberNavController()
    navigator.setNavController(navController)

    Scaffold(
        bottomBar = {
            if (navigator.checkDestination()) {
                BottomBar(navigator)
            }
        }
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(it)
        ) {
            navigator.buildNavHost()
        }
    }
}
