package com.wagnod.home.goals

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.wagnod.core_ui.navigation.BackButton
import com.wagnod.core_ui.navigation.NavBar
import com.wagnod.core_ui.navigation.NavBarTitle
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.theme.MentalMeTheme
import com.wagnod.domain.Goal
import com.wagnod.home.goals.GoalsContract.*
import org.koin.androidx.compose.getViewModel

@Composable
fun GoalsScreen(
    navigator: Navigator,
    viewModel: GoalsViewModel = getViewModel()
) {
    val listener = object : GoalsListener {
        override fun onCreateGoalButtonClicked() {
            viewModel.setEvent(Event.OnCreateGoalButtonClicked)
        }

        override fun onIndexedGoalChanged(index: Int, goal: Goal) {
            viewModel.setEvent(Event.OnCheckBoxClicked(index, goal))
        }

        override fun onGoalCardClicked(index: Int) {
            viewModel.setEvent(Event.OnGoalCardClicked(index))
        }
    }
    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init(-1))
    }

    GoalsContent(
        state = viewModel.viewState.value,
        listener = listener
    )

    LaunchedEffect(true) {
        viewModel.effect.collect { value ->
            when (value) {
                is Effect.NavigateToGoalsCreator -> {
                    navigator.homeNavigator.navigateToGoalCreator(value.index)
                }
                Effect.NavigateToGoalsScreen -> {
                    navigator.back()
                }
            }
        }
    }
}

@Composable
private fun GoalsContent(
    state: State,
    listener: GoalsListener?
) = Column(modifier = Modifier.fillMaxSize()) {

    NavBar(
        title = { NavBarTitle(title = "Цели") },
        navigationIcon = { BackButton {} }
    )

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (cards, button) = createRefs()

        val cardsModifier = Modifier.constrainAs(cards) {
            height = Dimension.fillToConstraints
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }
        GoalCards(cardsModifier, state, listener)

        val buttonModifier = Modifier.constrainAs(button) {
            end.linkTo(parent.end, 16.dp)
            bottom.linkTo(parent.bottom, 16.dp)
        }
        AddGoalButton(buttonModifier, listener)
    }
}

@Composable
private fun GoalCards(
    modifier: Modifier,
    state: State,
    listener: GoalsListener?
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(state.goals) { index, item ->
            if (item.name.isNotEmpty() && item.description.isNotEmpty()) {
                GoalCard(item, index + 1, state, listener)
            }
        }
    }
}

@Composable
private fun GoalCard(
    goal: Goal,
    index: Int,
    state: State,
    listener: GoalsListener?
) = ConstraintLayout(
    modifier = Modifier
        .fillMaxWidth()
        .clickable {
            listener?.onGoalCardClicked(index - 1)
        }
        .padding(vertical = 8.dp)
) {
    val (num, goalCard, checkbox) = createRefs()

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
            text = goal.name,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = goal.description,
            style = MaterialTheme.typography.h1,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
    }

    Checkbox(
        checked = goal.checked,
        onCheckedChange = {
            val curGoal = state.goals[index - 1]
            listener?.onIndexedGoalChanged(
                index - 1,
                Goal(
                    name = curGoal.name,
                    description = curGoal.description,
                    checked = (!curGoal.checked)
                )
            )
        },
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


@Composable
private fun AddGoalButton(
    modifier: Modifier,
    listener: GoalsListener?
) {
    FloatingActionButton(
        onClick = { listener?.onCreateGoalButtonClicked() },
        elevation = FloatingActionButtonDefaults.elevation(8.dp),
        backgroundColor = MaterialTheme.colors.primaryVariant,
        modifier = modifier
    ) {
        Icon(Icons.Filled.Add, "")
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() = MentalMeTheme {
    GoalsContent(state = State(), listener = null)
}