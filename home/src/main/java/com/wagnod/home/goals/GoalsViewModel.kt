package com.wagnod.home.goals

import com.wagnod.core_ui.view_model.BaseViewModel
import com.wagnod.core.datastore.user.Goal
import com.wagnod.domain.execute
import com.wagnod.domain.dashboard.usecase.GetUserInfoUseCase
import com.wagnod.domain.dashboard.usecase.PutGoalsToDatabaseUseCase
import com.wagnod.home.goals.GoalsContract.*
import com.wagnod.home.goals.data.TextFieldType
import kotlinx.coroutines.launch

class GoalsViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val putGoalsToDatabaseUseCase: PutGoalsToDatabaseUseCase,
) : BaseViewModel<Event, State, Effect>() {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> loadGoalsFromDatabase(event.index)
            is Event.OnCreateGoalButtonClicked -> navigateToGoalsCreator()
            is Event.OnSaveButtonClicked -> saveGoal(event.goal, event.index)
            is Event.OnGoalEdited -> editGoal(event.text, event.inputType)
            is Event.OnCheckBoxClicked -> changeCheckBoxState(event.index, event.goal)
            is Event.OnGoalCardClicked -> navigateToGoalsCreator(event.index)
        }
    }

    private fun editGoal(text: String, type: TextFieldType) = setState {
        copy(
            chosenGoal = if (type == TextFieldType.GOAL_NAME) {
                chosenGoal.copy(name = text)
            } else {
                chosenGoal.copy(description = text)
            }
        )
    }

    private fun loadGoalsFromDatabase(index: Int) = launch {
        runCatching {
            getUserInfoUseCase
                .execute()
                .collect { userInfo ->
                    if (userInfo != null) {
                        setGoals(userInfo.goals)
                        if (index != -1) {
                            setState {
                                copy(chosenGoal = viewState.value.goals[index])
                            }
                        }
                    }
                }
        }
    }

    private fun navigateToGoalsCreator(index: Int = -1) = setEffect {
        Effect.NavigateToGoalsCreator(index)
    }

    private fun setGoals(goals: List<Goal>) = setState {
        copy(goals = goals)
    }

    private fun updateGoals(toNavigate: Boolean) = launch {
        runCatching {
            putGoalsToDatabaseUseCase.execute(viewState.value.goals)
        }.onSuccess {
            if (toNavigate) navigateToGoalsScreen()
        }
    }

    private fun saveGoal(newGoal: Goal, editedGoalIndex: Int) {
        val list = mutableListOf<Goal>().apply {
            viewState.value.goals.forEachIndexed { index, goal ->
                if (editedGoalIndex == index) add(newGoal) else add(goal)
            }
            if (editedGoalIndex == -1) add(newGoal)
        }
        setGoals(list)
        updateGoals(true)
    }

    private fun navigateToGoalsScreen() = setEffect {
        Effect.NavigateToGoalsScreen
    }

    private fun changeCheckBoxState(index: Int, goal: Goal) {
        setState {
            copy(goals = viewState.value.goals.toMutableList().also { it[index] = goal })
        }
        updateGoals(false)
    }
}