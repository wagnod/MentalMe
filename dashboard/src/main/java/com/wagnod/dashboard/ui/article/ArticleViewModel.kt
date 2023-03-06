package com.wagnod.dashboard.ui.article

import com.wagnod.core.datastore.articles.Article
import com.wagnod.core_ui.view_model.BaseViewModel
import com.wagnod.dashboard.ui.article.ArticleContract.*

class ArticleViewModel() : BaseViewModel<Event, State, Effect>() {
    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> init(event.article)
        }
    }

    private fun init(article: Article) {
        setState { copy(article = article) }
    }
}