package com.wagnod.home.goals

import com.wagnod.core_ui.base.BaseViewModel
import com.wagnod.domain.Goal
import com.wagnod.domain.execute
import com.wagnod.domain.home.usecase.GetUserInfoUseCase
import com.wagnod.domain.home.usecase.PutGoalsUseCase
import com.wagnod.home.goals.GoalsContract.*
import kotlinx.coroutines.launch

class GoalsViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val putGoalsUseCase: PutGoalsUseCase,
) : BaseViewModel<Event, State, Effect>() {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> loadData()
            is Event.OnGoalsUpdated -> updateGoals(event.goals)
        }
    }

    private fun loadData() = launch {
        runCatching {
            getUserInfoUseCase
                .execute()
                .collect { data ->
                    if (data != null) changeData(data.goals)
                }
        }
    }

    private fun changeData(goals: List<Goal>) = setState {
        copy(goals = goals)
    }

    private fun updateGoals(goals: List<Goal>) = launch {
        runCatching {
            putGoalsUseCase.execute(goals)
        }
    }
}