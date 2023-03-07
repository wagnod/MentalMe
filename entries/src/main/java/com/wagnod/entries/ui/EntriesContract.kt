package com.wagnod.entries.ui

import com.wagnod.core_ui.sheet.data.BottomSheetParams
import com.wagnod.core_ui.view_model.ViewEvent
import com.wagnod.core_ui.view_model.ViewSideEffect
import com.wagnod.core_ui.view_model.ViewState
import com.wagnod.core.datastore.user.UserInfo

interface EntriesContract {
    interface Event : ViewEvent {
        object Init : Event
        data class ShowBottomSheet(val bottomSheetParams: BottomSheetParams) : Event
    }

    data class State(
        val user: UserInfo = UserInfo()
    ): ViewState

    sealed interface Effect : ViewSideEffect {}

    interface Listener {
        fun onProfileClick()
    }
}