package com.wagnod.domain.dashboard.usecase

import com.wagnod.core.datastore.articles.Article
import com.wagnod.domain.UseCase
import com.wagnod.domain.dashboard.repository.DashboardRepository
import kotlinx.coroutines.flow.StateFlow

class GetTodaySelectionUseCase(
    private val repository: DashboardRepository
):UseCase<Unit, StateFlow<List<Article>>> {
    override suspend fun execute(input: Unit): StateFlow<List<Article>> {
        return repository.getTodaySelection()
    }
}