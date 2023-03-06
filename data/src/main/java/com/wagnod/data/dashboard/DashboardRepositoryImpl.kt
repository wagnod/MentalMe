package com.wagnod.data.dashboard

import com.wagnod.core.datastore.articles.Article
import com.wagnod.core.datastore.articles.Daily
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
    private val dailiesFlow: MutableStateFlow<List<Daily>> = MutableStateFlow(listOf())

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

    override suspend fun getDailies(): StateFlow<List<Daily>> {
        return dailiesFlow
    }

    override suspend fun subscribeDailies() = withContext(dispatchers.io) {

        val listener = createEventListener<List<Daily>> { list ->
            dailiesFlow.emit(list)
        }
        val reference = references.getDailiesReference().also { ref ->
            ref.addValueEventListener(listener)
        }

        Pair(reference, listener)
    }
}
