package com.wagnod.home.goals

import com.wagnod.core_ui.base.ViewEvent
import com.wagnod.core_ui.base.ViewSideEffect
import com.wagnod.core_ui.base.ViewState
import com.wagnod.domain.Goal

interface GoalsContract {
    interface Event : ViewEvent {
        object Init : Event
        data class OnGoalsUpdated(val goals: List<Goal>) : Event
    }

    data class State(
        val goals: List<Goal> = listOf()
    ) : ViewState

    sealed interface Effect : ViewSideEffect {}

    interface Listener {}
}