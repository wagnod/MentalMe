package com.wagnod.dashboard.ui

import com.wagnod.core_ui.view_model.ViewEvent
import com.wagnod.core_ui.view_model.ViewSideEffect
import com.wagnod.core_ui.view_model.ViewState
import com.wagnod.domain.UserInfo

interface DashboardContract {

    interface Event : ViewEvent {
        data class OnProfileClick(val user: UserInfo) : Event
        data class OnArticleClick(val article: Article) : Event
    }

    data class State(
        val user: UserInfo = UserInfo(),
        val articles: List<Article> = listOf(
            Article(
                title = "Meditation",
                image = "https://i0.wp.com/www.additudemag.com/wp-content/uploads/2022/04/Calming-Triggered-Emotions-Saunders_1920x1080.jpg",
                description = "",
                ArticleType.TEXT
            ),
            Article(
                title = "Self therapy",
                image = "https://i0.wp.com/www.additudemag.com/wp-content/uploads/2022/04/Calming-Triggered-Emotions-Saunders_1920x1080.jpg",
                description = "",
                ArticleType.AUDIO
            )
        )
    ) : ViewState

    interface Effect : ViewSideEffect

    interface Listener {
        fun onProfileClick(user: UserInfo)
        fun onArticleClick(article: Article)
    }
}