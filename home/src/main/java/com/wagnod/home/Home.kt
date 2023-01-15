package com.wagnod.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.home.data.getCardsData

@Composable
fun HomeScreen(navigator: Navigator) =
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar()
        HomeCards(navigator = navigator)
    }


@Composable
fun TopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .height(56.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            text = "Главная",
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
fun HomeCards(navigator: Navigator) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        getCardsData(navigator = navigator).forEachIndexed { index, it ->
            HomeScreenCard(
                index = index,
                icon = it.icon,
                name = it.name,
                text = it.text,
                onClick = it.onclick
            )
        }
    }
}

@Composable
fun HomeScreenCard(
    index: Int,
    icon: Int,
    name: String,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 150.dp)
            .clickable { }
            .padding(top = if (index == 0) 0.dp else 5.dp, bottom = 5.dp)
            .shadow(elevation = 2.dp)
            .clickable { onClick() }

    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "",
            modifier = Modifier
                .padding(start = 16.dp)
                .size(70.dp)
                .align(alignment = Alignment.Companion.CenterVertically),
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(alignment = Alignment.Companion.CenterVertically)
        ) {
            Text(
                text = name,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
            )
        }
    }
}
