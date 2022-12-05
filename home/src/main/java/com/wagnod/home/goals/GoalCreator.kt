package com.wagnod.home.goals

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.wagnod.core_ui.Navigator
import com.wagnod.core_ui.theme.MentalMeTheme
import com.wagnod.domain.Goal
import com.wagnod.home.R

private lateinit var goalItem: Goal

@Composable
fun GoalCreator(
    navigator: Navigator? = null
) = Column(Modifier.fillMaxSize()) {
    ToolBar(navigator)
    val title = goalTitle()
    val text = goalText()
    goalItem = Goal(title, text, false)
}

@Composable
fun ToolBar(
    navigator: Navigator? = null
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .height(56.dp)
            .padding(horizontal = 16.dp),

        ) {
        val (back, title, save) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "",
            modifier = Modifier
                .size(24.dp)
                .constrainAs(back) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(title.start)
                }
                .clickable { navigator?.homeNavigator?.navigateToGoals() }
        )
        Text(
            text = "Добавить цель",
            modifier = Modifier
                .padding(start = 24.dp)
                .constrainAs(title) {
                    width = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(back.end)
                    end.linkTo(save.start)
                },
            style =
            TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Image(
            painter = painterResource(id = R.drawable.ic_confirm),
            contentDescription = "",
            modifier = Modifier
                .size(24.dp)
                .constrainAs(save) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(title.end)
                    end.linkTo(parent.end)
                }
                .clickable { navigator?.homeNavigator?.navigateToGoals() }
        )
    }
}

@Composable
fun goalTitle(): String {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        modifier = Modifier
            .fillMaxWidth(),
        label = { Text(text = "Заголовок") },
        placeholder = { Text(text = "Введите название цели") },
        colors = TextFieldDefaults
            .textFieldColors(
                backgroundColor = Color.White
            )
    )
    return text.text
}

@Composable
fun goalText(): String {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        modifier = Modifier
            .fillMaxSize(),
        placeholder = { Text(text = "Введите описание цели") },
        colors = TextFieldDefaults
            .textFieldColors(
                backgroundColor = Color.White
            )
    )
    return text.text
}

@Preview(showBackground = true)
@Composable
fun PreviewGoalCreator() {
    MentalMeTheme {
        GoalCreator()
    }
}
