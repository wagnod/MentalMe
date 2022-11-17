package com.wagnod.mentalme.ui.home.goals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.wagnod.mentalme.R
import com.wagnod.mentalme.ui.home.goals.data.GoalItem
import com.wagnod.mentalme.ui.home.goals.data.getGoals
import com.wagnod.mentalme.ui.theme.MentalMeTheme

@Composable
fun GoalCard(goal: GoalItem) {
    Card(
        modifier = Modifier.fillMaxWidth(1f)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            val checkedState = remember { mutableStateOf(goal.isDone) }
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                modifier = Modifier
                    .align(alignment = CenterVertically)
                    .padding(start = 16.dp)
            )

            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = goal.title,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = goal.description,
                    style = TextStyle(color = Color.Gray, fontSize = 16.sp)
                )
            }
        }
    }
}

@Composable
fun Goals() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (topBar, cards, button) = createRefs()
        Row(

            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.base_blue))
                .height(50.dp)
                .constrainAs(topBar) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
//                    bottom.linkTo(cards.top)
                },
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Цели",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        LazyColumn(
            modifier = Modifier.constrainAs(cards) {
                height = Dimension.fillToConstraints
                top.linkTo(topBar.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }) {
            val list = getGoals()
            items(list) {
                GoalCard(it)
            }
        }
        FloatingActionButton(
            onClick = { /*TODO*/ },
            elevation = FloatingActionButtonDefaults.elevation(8.dp),
            modifier = Modifier
                .constrainAs(button) {
                    end.linkTo(parent.end, 16.dp)
                    bottom.linkTo(parent.bottom, 16.dp)
                }
        ) {
            Icon(Icons.Filled.Add, "")

        }

    }

}


@Preview(showBackground = true)
@Composable

fun PreviewAppBar() {
    MentalMeTheme {
        Goals()
    }
}