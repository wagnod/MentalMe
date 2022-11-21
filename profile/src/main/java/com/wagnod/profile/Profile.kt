package com.wagnod.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.wagnod.core_ui.theme.MentalMeTheme
import com.wagnod.profile.data.User


@Composable
fun Profile() {
    Column(modifier = Modifier.fillMaxSize()) {
        ToolBar()
        UserInfo(
            user = User(
                R.drawable.ic_profile_icon,
                name = "User",
                lastName = "User",
                id = 1234567890,
                status = "Fill Depressed"
            )
        )
    }
}

@Composable
fun ToolBar() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .height(56.dp)
            .padding(horizontal = 16.dp),

        ) {
        val (title, options) = createRefs()
        Column(modifier = Modifier
            .constrainAs(title) {
                width = Dimension.fillToConstraints
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(options.start)
            }) {

            Text(
                modifier = Modifier
                    .padding(start = 24.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                text = "Профиль",
                style =
                TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_more),
            contentDescription = "",
            modifier = Modifier
                .size(24.dp)
                .constrainAs(options) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(title.end)
                    end.linkTo(parent.end)
                }
                .clickable { }
        )
    }
}

@Composable
fun UserInfo(user: User) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.ic_profile_icon),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp)
                .size(75.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = user.name + " " + user.lastName,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(fontSize = 20.sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfile() = MentalMeTheme {
    Profile()
}