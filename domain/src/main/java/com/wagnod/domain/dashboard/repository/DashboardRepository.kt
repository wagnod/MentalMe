package com.wagnod.domain.dashboard.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.wagnod.core.datastore.articles.Article
import com.wagnod.core.datastore.articles.Daily
import kotlinx.coroutines.flow.StateFlow

interface DashboardRepository {
    suspend fun getTodaySelection(): StateFlow<List<Article>>
    suspend fun subscribeTodaySelection(): Pair<DatabaseReference, ValueEventListener>
    suspend fun getDailies(): StateFlow<List<Daily>>
    suspend fun subscribeDailies(): Pair<DatabaseReference, ValueEventListener>
}