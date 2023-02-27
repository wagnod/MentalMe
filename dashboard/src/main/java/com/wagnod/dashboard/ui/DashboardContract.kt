package com.wagnod.dashboard.ui

import com.wagnod.core_ui.view_model.ViewEvent
import com.wagnod.core_ui.view_model.ViewSideEffect
import com.wagnod.core_ui.view_model.ViewState
import com.wagnod.domain.UserInfo

interface DashboardContract {

    interface Event : ViewEvent {
        object Init : Event
        data class OnProfileClick(val user: UserInfo) : Event
        data class OnArticleClick(val article: Article) : Event
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