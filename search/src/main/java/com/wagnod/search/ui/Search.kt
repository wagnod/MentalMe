package com.wagnod.search.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wagnod.core_ui.navigation.NavBar
import com.wagnod.core_ui.navigation.NavBarTitle
import com.wagnod.core_ui.navigators.main.Navigator

@Composable
fun Search(
    navigator: Navigator
) {
    Column(modifier = Modifier.fillMaxSize()) {
        NavBar(title = { NavBarTitle(title = "Поиск") })
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "There will be a new screen")
        }
    }
}
