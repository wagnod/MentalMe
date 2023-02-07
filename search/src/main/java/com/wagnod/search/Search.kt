package com.wagnod.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wagnod.core_ui.navigation.NavBar
import com.wagnod.core_ui.navigation.NavBarTitle

@Composable
fun Search() {
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

@Preview(showBackground = true)
@Composable
fun PreviewSearch() {
    Search()
}