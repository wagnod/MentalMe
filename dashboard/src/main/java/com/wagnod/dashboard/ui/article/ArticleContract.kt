package com.wagnod.dashboard.ui.article

import com.wagnod.core.datastore.articles.Article
import com.wagnod.core_ui.view_model.ViewEvent
import com.wagnod.core_ui.view_model.ViewSideEffect
import com.wagnod.core_ui.view_model.ViewState

interface ArticleContract {
    interface Event : ViewEvent {
        data class Init(val article: Article) : Event
    }

    data class State(
        val article: Article = Article(),
    ) : ViewState

    sealed interface Effect : ViewSideEffect {}

    interface Listener {}
}