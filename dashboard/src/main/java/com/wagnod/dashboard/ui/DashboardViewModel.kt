package com.wagnod.dashboard.ui

import com.wagnod.core_ui.sheet.ShowBottomSheetHelper
import com.wagnod.core_ui.sheet.data.BottomSheetParams
import com.wagnod.core_ui.view_model.BaseViewModel
import com.wagnod.dashboard.ui.DashboardContract.*
import com.wagnod.dashboard.ui.DashboardContract.Event.*
import com.wagnod.core.datastore.user.UserInfo
import com.wagnod.domain.dashboard.usecase.GetDailiesUseCase
import com.wagnod.domain.dashboard.usecase.GetTodaySelectionUseCase
import com.wagnod.domain.execute
import com.wagnod.domain.dashboard.usecase.GetUserInfoUseCase
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val showBottomSheetHelper: ShowBottomSheetHelper,
    private val getTodaySelectionUseCase: GetTodaySelectionUseCase,
    private val getDailiesUseCase: GetDailiesUseCase
) : BaseViewModel<Event, State, Effect>() {
    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Init -> init()
            is ShowBottomSheet -> showBottomSheet(event.bottomSheetParams)
            is OnArticleClick -> setEffect { Effect.ShowFullArticle(event.article) }
            is OnGeneralSheetClick -> {}
            is OnFeedbackSheetClick -> {}
        }
    }

    private fun init() = io {
        runCatching {
            getUser()
            getTodaySelection()
            getDailies()
        }
    }

    private fun getUser() = launch {
        getUserInfoUseCase.execute().collect { user ->
            setState { copy(user = user ?: UserInfo()) }
        }
    }

    private fun getTodaySelection() = launch {
        val res = getTodaySelectionUseCase.execute()
        res.collect { selection ->
            setState { copy(articles = selection) }
        }
    }

    private fun getDailies() = launch {
        getDailiesUseCase.execute().collect { dailies ->
            setState { copy(dailies = dailies) }
        }
    }

    private fun showBottomSheet(bottomSheetParams: BottomSheetParams) {
        showBottomSheetHelper.showItems(bottomSheetParams)
    }
}