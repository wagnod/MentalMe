@file:OptIn(ExperimentalMaterialApi::class)

package com.wagnod.mentalme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.sheet.SheetLayout
import com.wagnod.core_ui.sheet.ShowBottomSheetHelper
import com.wagnod.core_ui.theme.MentalMeTheme
import com.wagnod.login.ui.auth.AuthContract.*
import com.wagnod.login.ui.auth.AuthViewModel
import com.wagnod.navigation.data.NavSections
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val navigator by inject<Navigator>()
    private val viewModel by viewModel<AuthViewModel>()
    private val showBottomSheetHelper by inject<ShowBottomSheetHelper>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            val scope = rememberCoroutineScope()
            val scaffoldState = rememberScaffoldState()
            val bottomSheetState = rememberModalBottomSheetState(
                initialValue = ModalBottomSheetValue.Hidden,
                skipHalfExpanded = true
            )

            showBottomSheetHelper.init(scope, bottomSheetState)

            LaunchedEffect(Unit) {
                snapshotFlow { bottomSheetState.currentValue }
                    .collect {
                        if (it == ModalBottomSheetValue.Hidden) showBottomSheetHelper.closeSheet()
                    }
            }

            val bottomSheetContent: @Composable () -> Unit = {
                showBottomSheetHelper.showItems.collectAsState(null).value?.let { params ->
                    SheetLayout(params)
                }
                Spacer(modifier = Modifier.height(1.dp))
            }

            ScaffoldMentalMe(
                navigator = navigator,
                navController = navController,
                scaffoldState = scaffoldState,
                bottomSheetState = bottomSheetState,
                bottomSheetContent = bottomSheetContent,
            ) {
//                NavHost(
//                    navController = navController,
//                    startDestination = "splash_screen",
//                    builder = {
                        navigator.buildNavHost()
//                    }
//                )
            }
            LaunchedEffect(true) {
                viewModel.effect.collect { value ->
                    when (value) {
                        Effect.NavigateToHome -> navigator.navigateToHomeAndClear()
                        Effect.NavigateToLoginScreen -> navigator.navigateToLogin()
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
        backgroundColor = MaterialTheme.colors.background
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
fun ScaffoldMentalMe(
    navigator: Navigator,
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    bottomSheetState: ModalBottomSheetState,
    bottomSheetContent: @Composable () -> Unit,
    setGraphs: @Composable () -> Unit
) {
    navigator.setNavController(navController)

    MentalMeTheme {
        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
            sheetContent = { bottomSheetContent() }
        ) {
            Scaffold(
                scaffoldState = scaffoldState,
                bottomBar = {
                    if (navigator.checkDestination()) {
                        BottomBar(navigator)
                    }
                }
            ) {
                Box(
                    modifier = Modifier.padding(it)
                ) {
                    setGraphs()
                }
            }
        }
    }
}
