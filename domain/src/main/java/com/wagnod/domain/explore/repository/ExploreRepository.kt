package com.wagnod.domain.explore.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.wagnod.core.datastore.articles.Category
import kotlinx.coroutines.flow.StateFlow

interface ExploreRepository {
    suspend fun getCategories(): StateFlow<List<Category>>
    suspend fun subscribeCategories(): Pair<DatabaseReference, ValueEventListener>
}