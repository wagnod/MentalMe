package com.wagnod.mentalme.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wagnod.mentalme.R
import com.wagnod.mentalme.navigation.Navigator
import com.wagnod.mentalme.ui.data.getCardsData
import com.wagnod.mentalme.ui.theme.MentalMeTheme


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
                .align(alignment = CenterVertically),
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(alignment = CenterVertically)
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

@Composable
fun HomeScreen(navigator: Navigator) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.base_blue))
                .height(50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = "Главная",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
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
}

@Preview(showBackground = true)
@Composable

fun PreviewCard() {
    MentalMeTheme {
//        HomeScreen()
    }
}