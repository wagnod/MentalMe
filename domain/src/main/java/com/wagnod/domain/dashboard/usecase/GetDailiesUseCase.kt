package com.wagnod.domain.dashboard.usecase

import com.wagnod.core.datastore.articles.Daily
import com.wagnod.domain.UseCase
import com.wagnod.domain.dashboard.repository.DashboardRepository
import kotlinx.coroutines.flow.StateFlow

class GetDailiesUseCase(
    private val repository: DashboardRepository
) : UseCase<Unit, StateFlow<List<Daily>>> {
    override suspend fun execute(input: Unit): StateFlow<List<Daily>> {
        return repository.getDailies()
    }
}