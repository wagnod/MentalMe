package com.wagnod.home.goals

import com.wagnod.core_ui.view_model.ViewEvent
import com.wagnod.core_ui.view_model.ViewSideEffect
import com.wagnod.core_ui.view_model.ViewState
import com.wagnod.core.datastore.user.Goal
import com.wagnod.home.goals.data.TextFieldType

interface GoalsContract {
    interface Event : ViewEvent {
        data class Init(val index: Int) : Event
        object OnCreateGoalButtonClicked : Event
        data class OnSaveButtonClicked(val goal: Goal, val index: Int) : Event
        data class OnGoalEdited(val text: String, val inputType: TextFieldType) : Event
        data class OnCheckBoxClicked(val index: Int, val goal: Goal) : Event
        data class OnGoalCardClicked(val index: Int) : Event
    }

    data class State(
        val goals: List<Goal> = listOf(),
        val chosenGoal: Goal = Goal()
    ) : ViewState

    sealed interface Effect : ViewSideEffect {
        data class NavigateToGoalsCreator(val index: Int) : Effect
        object NavigateToGoalsScreen : Effect
    }

    interface GoalsListener {
        fun onCreateGoalButtonClicked()
        fun onIndexedGoalChanged(index: Int, goal: Goal)
        fun onGoalCardClicked(index: Int)
    }

    interface GoalsCreatorListener{
        fun onSaveButtonClicked(goal: Goal)
        fun onChosenGoalChanged(text: String, type: TextFieldType)
    }
}