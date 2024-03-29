package com.wagnod.explore.ui

import com.wagnod.core.datastore.user.UserInfo
import com.wagnod.core_ui.sheet.ShowBottomSheetHelper
import com.wagnod.core_ui.sheet.data.BottomSheetParams
import com.wagnod.core_ui.view_model.BaseViewModel
import com.wagnod.domain.dashboard.usecase.GetUserInfoUseCase
import com.wagnod.domain.execute
import com.wagnod.domain.explore.usecase.GetCategoriesUseCase
import com.wagnod.explore.ui.ExploreContract.*
import com.wagnod.explore.ui.ExploreContract.Event.*
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val showBottomSheetHelper: ShowBottomSheetHelper,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
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
            getCategories()
        }
    }

    private fun getUser() = launch {
        getUserInfoUseCase.execute().collect { user ->
            setState { copy(user = user ?: UserInfo()) }
        }
    }

    private fun getCategories() = launch {
        getCategoriesUseCase.execute().collect { categories ->
            setState { copy(categories = categories) }
        }
    }

    private fun showBottomSheet(bottomSheetParams: BottomSheetParams) {
        showBottomSheetHelper.showItems(bottomSheetParams)
    }
}