package com.wagnod.home.goals

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.wagnod.core_ui.Navigator
import com.wagnod.core_ui.theme.MentalMeTheme
import com.wagnod.home.R
import com.wagnod.home.goals.data.GoalItem
import com.wagnod.home.goals.data.getGoals

@Composable
fun Goals(
    navigator: Navigator? = null
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (topBar, cards, button) = createRefs()
        val topModifier = Modifier.constrainAs(topBar) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        }
        val cardsModifier = Modifier.constrainAs(cards) {
            height = Dimension.fillToConstraints
            top.linkTo(topBar.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }
        val buttonModifier = Modifier.constrainAs(button) {
            end.linkTo(parent.end, 16.dp)
            bottom.linkTo(parent.bottom, 16.dp)
        }

        TopAppBar(modifier = topModifier)
        GoalCards(modifier = cardsModifier)
        AddGoalButton(modifier = buttonModifier, navigator = navigator)
    }
}

@Composable
fun TopAppBar(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .height(56.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = CenterVertically
    ) {
        Text(
            text = "Цели",
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
fun GoalCards(modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        val list = getGoals()
        itemsIndexed(list) { index, item ->
            GoalCard(item, index + 1)
        }
    }
}

@Composable
fun GoalCard(goal: GoalItem, index: Int) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 8.dp)

    ) {
        val (num, goalCard, checkbox) = createRefs()
        val checkedState = remember { mutableStateOf(goal.isDone) }

        Text(
            text = index.toString(),
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(num) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(goalCard) {
                    width = Dimension.fillToConstraints
                    top.linkTo(num.top)
                    start.linkTo(num.end)
                    end.linkTo(checkbox.start)
                }
        ) {
            Text(
                text = goal.title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = goal.description,
                style = MaterialTheme.typography.h1
            )
        }

        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(checkbox) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(goalCard.end)
                    end.linkTo(parent.end)
                }
        )

    }
}


@Composable
fun AddGoalButton(
    modifier: Modifier,
    navigator: Navigator? = null
) {
    FloatingActionButton(
        onClick = { navigator?.homeNavigator?.navigateToGoalCreator() },
        elevation = FloatingActionButtonDefaults.elevation(8.dp),
        modifier = modifier
    ) {
        Icon(Icons.Filled.Add, "")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGoals() {
    MentalMeTheme {
        Goals()
    }
}
