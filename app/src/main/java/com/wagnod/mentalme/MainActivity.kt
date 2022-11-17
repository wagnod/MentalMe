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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.wagnod.core_ui.Navigator
import com.wagnod.navigation.data.NavSections
import com.wagnod.mentalme.ui.theme.MentalMeTheme
import org.koin.android.ext.android.inject

class MainActivity() : ComponentActivity() {

    private val navigator by inject<Navigator>()

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
        }
    }
}

@Composable
fun BottomBar(navigator: Navigator) {

    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(backgroundColor = colorResource(id = R.color.base_blue)) {
        val sections = NavSections.values()
        sections.forEach {
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(it.icon),
                        "home navigation button",
                        modifier = Modifier.size(30.dp)
                    )
                }, label = { Text(stringResource(it.title)) },
                alwaysShowLabel = false,
                selected = (selectedIndex.value == it.value),
                onClick = {
                    it.tabNavigationCallback(navigator)
                    selectedIndex.value = it.value
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
        bottomBar = { BottomBar(navigator) }
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
