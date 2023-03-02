package com.wagnod.dashboard.ui

import com.wagnod.core.datastore.articles.Article
import com.wagnod.core.datastore.user.UserInfo
import com.wagnod.core_ui.sheet.data.BottomSheetParams
import com.wagnod.core_ui.sheet.data.FeedbackSheetItems
import com.wagnod.core_ui.sheet.data.GeneralSheetItems
import com.wagnod.core_ui.view_model.ViewEvent
import com.wagnod.core_ui.view_model.ViewSideEffect
import com.wagnod.core_ui.view_model.ViewState

interface DashboardContract {

    interface Event : ViewEvent {
        object Init : Event
        data class ShowBottomSheet(val bottomSheetParams: BottomSheetParams) : Event
        data class OnArticleClick(val article: Article) : Event
        data class OnGeneralSheetClick(val type: GeneralSheetItems): Event
        data class OnFeedbackSheetClick(val type: FeedbackSheetItems): Event
    }

    data class State(
        val user: UserInfo = UserInfo(),
        val articles: List<Article> = listOf()
    ) : ViewState

    sealed interface Effect : ViewSideEffect {
        data class ShowFullArticle(val article: Article) : Effect
    }

    interface Listener {
        fun onProfileClick()
        fun onArticleClick(article: Article)
    }
}