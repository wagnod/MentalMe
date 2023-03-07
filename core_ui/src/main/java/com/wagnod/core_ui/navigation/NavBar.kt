package com.wagnod.core_ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil.CoilImage
import com.wagnod.core_ui.R

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

@Composable
fun ProfileNavBar(
    userImage: Any?,
    actions: @Composable RowScope.() -> Unit = {},
    modifier: Modifier = Modifier,
    onUserClick: () -> Unit
) {
    Surface(modifier) {
        Box(
            Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(NAV_BAR_HEIGHT.dp)
        ) {
            Box(Modifier
                .align(Alignment.CenterStart)
                .padding(start = 24.dp)
            ) {
                CoilImage(
                    imageModel = {
                        userImage
                    },
                    modifier = Modifier
                        .clickable { onUserClick.invoke() }
                        .size(40.dp)
                        .clip(RoundedCornerShape(4.dp))
                )
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