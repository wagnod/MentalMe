package com.wagnod.domain.dashboard.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.wagnod.core.datastore.articles.Article
import kotlinx.coroutines.flow.MutableStateFlow

interface DashboardRepository {
    suspend fun getTodaySelection(): MutableStateFlow<List<Article>>
    suspend fun subscribeTodaySelection(): Pair<DatabaseReference, ValueEventListener>
}