package com.wagnod.profile.ui

import com.wagnod.core_ui.view_model.BaseViewModel
import com.wagnod.domain.execute
import com.wagnod.domain.home.usecase.GetUserInfoUseCase
import com.wagnod.profile.data.User
import com.wagnod.profile.ui.ProfileContract.*
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val mapper: ProfileViewModelMapper
) : BaseViewModel<Event, State, Effect>() {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> loadData()
            is Event.OnDataChanged -> changeData(user = event.user)
            is Event.OnEditButtonClicked -> setEffect { Effect.NavigateToEditScreen }
        }
    }

    private fun loadData() = launch {
        runCatching {
            getUserInfoUseCase
                .execute()
                .collect { data ->
                    if (data != null) changeData(mapper.mapUserInfoToUser(data))
                }
        }
    }

    private fun changeData(user: User) = setState {
        copy(
            user = user.copy(
                id = user.id,
                profileImage = user.profileImage,
                name = user.name,
                status = user.status
            )
        )
    }
}