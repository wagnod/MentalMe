package com.wagnod.data.dashboard

import com.wagnod.core.datastore.articles.Article
import com.wagnod.core.extensions.createEventListener
import com.wagnod.domain.AppDispatchers
import com.wagnod.domain.dashboard.repository.DashboardRepository
import com.wagnod.domain.firebase.FirebaseDatabaseReferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class DashboardRepositoryImpl(
    private val dispatchers: AppDispatchers,
    private val references: FirebaseDatabaseReferences
) : DashboardRepository {
    private val todayFlow: MutableStateFlow<List<Article>> = MutableStateFlow(listOf())

    override suspend fun getTodaySelection(): StateFlow<List<Article>> {
        return todayFlow
    }

    override suspend fun subscribeTodaySelection() = withContext(dispatchers.io) {

        val listener = createEventListener<List<Article>> { list ->
            todayFlow.emit(list)
        }
        val reference = references.getTodaySelectionReference().also { ref ->
            ref.addValueEventListener(listener)
        }

        Pair(reference, listener)
    }
}
