package com.wagnod.dashboard.ui

import com.wagnod.core_ui.sheet.ShowBottomSheetHelper
import com.wagnod.core_ui.sheet.data.BottomSheetItemsHelper
import com.wagnod.core_ui.view_model.BaseViewModel
import com.wagnod.dashboard.ui.DashboardContract.*
import com.wagnod.dashboard.ui.DashboardContract.Event.*
import com.wagnod.domain.UserInfo
import com.wagnod.domain.execute
import com.wagnod.domain.home.usecase.GetUserInfoUseCase
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val showBottomSheetHelper: ShowBottomSheetHelper,
    private val bottomSheetItemsHelper: BottomSheetItemsHelper
) : BaseViewModel<Event, State, Effect>() {
    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Init -> init()
            is OnProfileClick -> showBottomSheet()
            is OnArticleClick -> setEffect { Effect.ShowFullArticle(event.article) }
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

    private fun showBottomSheet() {
        showBottomSheetHelper.showItems(bottomSheetItemsHelper.getGeneralSheetItems())
    }
}