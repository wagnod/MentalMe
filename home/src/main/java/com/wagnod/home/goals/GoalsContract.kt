package com.wagnod.home.goals

import com.wagnod.core_ui.base.ViewEvent
import com.wagnod.core_ui.base.ViewSideEffect
import com.wagnod.core_ui.base.ViewState
import com.wagnod.domain.Goal

interface GoalsContract {
    interface Event : ViewEvent {
        object Init : Event
        data class OnGoalsUpdated(val goals: List<Goal>) : Event
        object OnCreateGoalButtonClicked : Event
        data class OnSaveButtonClicked(val goal: Goal) : Event
        data class OnChosenGoalChanged(val goal: Goal) : Event
        data class OnIndexedGoalChanged(val index: Int, val goal: Goal) : Event
    }

    data class State(
        val goals: List<Goal> = listOf(),
        val chosenGoal: Goal = Goal()
    ) : ViewState

    sealed interface Effect : ViewSideEffect {
        object NavigateToGoalsCreator : Effect
        object NavigateToGoalsScreen : Effect
    }

    interface GoalsListener {
        fun onCreateGoalButtonClicked()
        fun onIndexedGoalChanged(index: Int, goal: Goal)
    }

    interface GoalsCreatorListener{
        fun onSaveButtonClicked(goal: Goal)
        fun onChosenGoalChanged(goal: Goal)
    }
}