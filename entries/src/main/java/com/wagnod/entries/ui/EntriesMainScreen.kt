package com.wagnod.entries.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wagnod.core_ui.navigation.NavBar
import com.wagnod.core_ui.navigation.NavBarTitle
import com.wagnod.core_ui.navigators.main.Navigator

@Composable
fun EntriesMainScreen(
    navigator: Navigator
) {
    Column(modifier = Modifier.fillMaxSize()) {
        NavBar(title = { NavBarTitle(title = "Добавить") })
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "I need to recreate my design, cause I don't know wtf this screen is"
            )
        }
    }
}
