package com.wagnod.new_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wagnod.core_ui.navigation.NavBar
import com.wagnod.core_ui.navigation.NavBarTitle

@Composable
fun NewScreen() {
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

@Preview(showBackground = true)
@Composable
fun PreviewNewScreen() {
    NewScreen()
}