package com.wagnod.home.goals

import com.wagnod.core_ui.base.BaseViewModel
import com.wagnod.domain.Goal
import com.wagnod.domain.execute
import com.wagnod.domain.home.usecase.GetUserInfoUseCase
import com.wagnod.domain.home.usecase.PutGoalsToDatabaseUseCase
import com.wagnod.home.goals.GoalsContract.*
import kotlinx.coroutines.launch

class GoalsViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val putGoalsToDatabaseUseCase: PutGoalsToDatabaseUseCase,
) : BaseViewModel<Event, State, Effect>() {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> loadGoals()
            is Event.OnGoalsUpdated -> updateGoals()
            is Event.OnCreateGoalButtonClicked -> createGoalButtonClicked()
            is Event.OnSaveButtonClicked -> addNewGoal(event.goal)
            is Event.OnChosenGoalChanged -> changeChosenGoal(event.goal)
            is Event.OnIndexedGoalChanged -> onIndexedGoalChanged(event.index, event.goal)
        }
    }

    private fun changeChosenGoal(goal: Goal) = setState {
        copy(chosenGoal = goal)
    }

    private fun loadGoals() = launch {
        runCatching {
            getUserInfoUseCase
                .execute()
                .collect { data ->
                    if (data != null) changeData(data.goals)
                }
        }
    }

    private fun createGoalButtonClicked() = setEffect {
        Effect.NavigateToGoalsCreator
    }

    private fun changeData(goals: List<Goal>) = setState {
        copy(goals = goals)
    }

    private fun updateGoals() = launch {
        runCatching {
            putGoalsToDatabaseUseCase.execute(viewState.value.goals)
        }.onSuccess {
            navigateToGoalsScreen()
        }
    }

    private fun addNewGoal(goal: Goal) {
        changeData(viewState.value.goals.toMutableList().also { it.add(goal) })
        updateGoals()
    }

    private fun navigateToGoalsScreen() = setEffect {
        Effect.NavigateToGoalsScreen
    }

    private fun onIndexedGoalChanged(index: Int, goal: Goal) {
        setState {
            copy(goals = viewState.value.goals.toMutableList().also { it[index] = goal })
        }
        updateGoals()
    }
}