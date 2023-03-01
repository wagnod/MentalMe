package com.wagnod.home.goals

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core.datastore.user.Goal
import com.wagnod.home.R
import com.wagnod.home.goals.GoalsContract.*
import com.wagnod.home.goals.data.TextFieldType
import com.wagnod.home.goals.data.TextFieldType.*
import org.koin.androidx.compose.getViewModel

@Composable
fun GoalCreator(
    navigator: Navigator,
    chosenGoalIndex: Int?,
    viewModel: GoalsViewModel = getViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init(chosenGoalIndex ?: -1))
    }

    val state = viewModel.viewState.value
    val listener = object : GoalsCreatorListener {
        override fun onSaveButtonClicked(goal: Goal) {
            viewModel.setEvent(Event.OnSaveButtonClicked(goal, chosenGoalIndex ?: -1))
        }

        override fun onChosenGoalChanged(text: String, type: TextFieldType) {
            viewModel.setEvent(Event.OnGoalEdited(text, type))
        }
    }

    LaunchedEffect(true) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is Effect.NavigateToGoalsScreen -> {
                    navigator.back()
                }
                is Effect.NavigateToGoalsCreator -> {
                    navigator.dashboardNavigator.navigateToGoalCreator(effect.index)
                }
            }
        }
    }

    GoalCreatorContent(navigator, listener, state)
}

@Composable
fun GoalCreatorContent(
    navigator: Navigator,
    listener: GoalsCreatorListener,
    state: State
) = Column(
    modifier = Modifier.fillMaxSize()
) {
    ToolBar(navigator, listener, state)
    goalTitle(state, listener)
    goalText(state, listener)
}

@Composable
fun ToolBar(
    navigator: Navigator,
    listener: GoalsCreatorListener,
    state: State
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
                .clickable { navigator.back() }
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
                .clickable { listener.onSaveButtonClicked(state.chosenGoal) }
        )
    }
}

@Composable
fun goalTitle(
    state: State,
    listener: GoalsCreatorListener
) {
    TextField(
        value = state.chosenGoal.name,
        onValueChange = {
            listener.onChosenGoalChanged(it, GOAL_NAME)
        },
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = "Заголовок")
        },
        placeholder = {
            Text(text = "Введите название цели")
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White
        )
    )
}

@Composable
fun goalText(
    state: State,
    listener: GoalsCreatorListener
) {
    TextField(
        value = state.chosenGoal.description,
        onValueChange = {
            listener.onChosenGoalChanged(it, GOAL_DESCRIPTION)
        },
        modifier = Modifier.fillMaxSize(),
        placeholder = { Text(text = "Введите описание цели") },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White
        )
    )
}

