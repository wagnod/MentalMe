package com.wagnod.home

import com.wagnod.core_ui.view_model.ViewEvent
import com.wagnod.core_ui.view_model.ViewSideEffect
import com.wagnod.core_ui.view_model.ViewState
import com.wagnod.domain.UserInfo
import com.wagnod.home.data.HomeCardType

interface HomeContract {

    interface Event: ViewEvent {
        object OnTrackerClick : Event
        object OnDiaryClick : Event
        object OnGoalsClick : Event
        data class OnProfileClick(val user: UserInfo) : Event
    }

    data class State(
        val user: UserInfo = UserInfo()
    ): ViewState

    interface Effect: ViewSideEffect {
        object NavigateToTracker : Effect
        object NavigateToDiary : Effect
        object NavigateToGoals : Effect
        data class ShowProfile(val user: UserInfo) : Effect
    }

    interface Listener {
        fun onCardClick(type: HomeCardType)
        fun onProfileClick()
    }
}