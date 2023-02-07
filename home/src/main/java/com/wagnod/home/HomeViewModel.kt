package com.wagnod.home

import com.wagnod.core_ui.view_model.BaseViewModel
import com.wagnod.home.HomeContract.*

class HomeViewModel(): BaseViewModel<Event, State, Effect>() {
    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.OnTrackerClick -> setEffect { Effect.NavigateToTracker }
            is Event.OnGoalsClick -> setEffect { Effect.NavigateToGoals }
            is Event.OnDiaryClick -> setEffect { Effect.NavigateToDiary }
            is Event.OnProfileClick -> setEffect { Effect.ShowProfile(event.user) }
        }
    }

}