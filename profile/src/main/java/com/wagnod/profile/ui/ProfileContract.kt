package com.wagnod.profile.ui

import com.wagnod.core_ui.view_model.ViewEvent
import com.wagnod.core_ui.view_model.ViewSideEffect
import com.wagnod.core_ui.view_model.ViewState
import com.wagnod.profile.data.User

interface ProfileContract {
    interface Event : ViewEvent {
        object Init : Event
        data class OnDataChanged(val user: User) : Event
        object OnEditButtonClicked : Event
    }

    data class State(
        val user: User = User.getSampleData()
    ) : ViewState


    sealed interface Effect : ViewSideEffect {
        object NavigateToEditScreen : Effect
    }

    interface Listener {
        fun onDataChanged(user: User)
    }
}