package com.wagnod.history.ui

import com.wagnod.core.datastore.user.UserInfo
import com.wagnod.core_ui.sheet.ShowBottomSheetHelper
import com.wagnod.core_ui.sheet.data.BottomSheetParams
import com.wagnod.core_ui.view_model.BaseViewModel
import com.wagnod.domain.dashboard.usecase.GetUserInfoUseCase
import com.wagnod.domain.execute
import com.wagnod.history.ui.HistoryContract.*
import com.wagnod.history.ui.HistoryContract.Event.*
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val showBottomSheetHelper: ShowBottomSheetHelper,
    private val getUserInfoUseCase: GetUserInfoUseCase,
) : BaseViewModel<Event, State, Effect>() {
    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Init -> init()
            is ShowBottomSheet -> showBottomSheet(event.bottomSheetParams)
        }
    }

    private fun init() = io {
        runCatching {
            getUser()
        }
    }

    private fun getUser() = launch {
        getUserInfoUseCase.execute().collect { user ->
            setState { copy(user = user ?: UserInfo()) }
        }
    }

    private fun showBottomSheet(bottomSheetParams: BottomSheetParams) {
        showBottomSheetHelper.showItems(bottomSheetParams)
    }
}