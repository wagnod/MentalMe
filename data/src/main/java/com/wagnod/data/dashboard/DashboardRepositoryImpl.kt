package com.wagnod.data.dashboard

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.wagnod.core.datastore.articles.Article
import com.wagnod.core.extensions.createEventListener
import com.wagnod.data.AppDispatchers
import com.wagnod.domain.firebase.FirebaseDatabaseReferences
import com.wagnod.domain.dashboard.repository.DashboardRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class DashboardRepositoryImpl(
    private val dispatchers: AppDispatchers,
    private val references: FirebaseDatabaseReferences
) : DashboardRepository {
    private val todayFlow: MutableStateFlow<List<Article>> = MutableStateFlow(listOf())

    override suspend fun getTodaySelection() = todayFlow

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
