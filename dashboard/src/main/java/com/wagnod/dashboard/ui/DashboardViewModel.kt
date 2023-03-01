package com.wagnod.dashboard.ui

import com.wagnod.core_ui.sheet.ShowBottomSheetHelper
import com.wagnod.core_ui.sheet.data.BottomSheetParams
import com.wagnod.core_ui.view_model.BaseViewModel
import com.wagnod.dashboard.ui.DashboardContract.*
import com.wagnod.dashboard.ui.DashboardContract.Event.*
import com.wagnod.core.datastore.user.UserInfo
import com.wagnod.domain.execute
import com.wagnod.domain.dashboard.usecase.GetUserInfoUseCase
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val showBottomSheetHelper: ShowBottomSheetHelper,
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

    private fun init() = launch {
        runCatching {
            getUserInfoUseCase
                .execute()
                .collect { user ->
                    setState { copy(user = user ?: UserInfo()) }
                }
        }
    }

    private fun showBottomSheet(bottomSheetParams: BottomSheetParams) {
        showBottomSheetHelper.showItems(bottomSheetParams)
    }
}