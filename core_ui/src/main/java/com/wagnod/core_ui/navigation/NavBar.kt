package com.wagnod.core_ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

const val NAV_BAR_HEIGHT = 56

@Composable
fun NavBar(
    title: @Composable BoxScope.() -> Unit = {},
    navigationIcon: @Composable BoxScope.() -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Surface(modifier) {
        Box(
            Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(NAV_BAR_HEIGHT.dp)
        ) {
            Box(Modifier.align(Alignment.CenterStart)) {
                navigationIcon()
            }
            Box(Modifier.align(Alignment.Center)) {
                title()
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
            ) {
                actions()
            }
        }
    }
}