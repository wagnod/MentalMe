package com.wagnod.dashboard.ui

import com.wagnod.core_ui.view_model.BaseViewModel
import com.wagnod.dashboard.ui.DashboardContract.*

class DashboardViewModel()
    : BaseViewModel<Event, State, Effect>(){
    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        TODO("Not yet implemented")
    }
}