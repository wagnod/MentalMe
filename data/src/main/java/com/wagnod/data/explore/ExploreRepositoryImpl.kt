package com.wagnod.data.explore

import com.wagnod.core.datastore.articles.Category
import com.wagnod.core.extensions.createEventListener
import com.wagnod.domain.AppDispatchers
import com.wagnod.domain.explore.repository.ExploreRepository
import com.wagnod.domain.firebase.FirebaseDatabaseReferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class ExploreRepositoryImpl(
    private val dispatchers: AppDispatchers,
    private val references: FirebaseDatabaseReferences
) : ExploreRepository {
    private val categoriesFlow: MutableStateFlow<List<Category>> = MutableStateFlow(listOf())


    override suspend fun getCategories(): StateFlow<List<Category>> {
        return categoriesFlow
    }

    override suspend fun subscribeCategories() = withContext(dispatchers.io) {

        val listener = createEventListener<List<Category>> { list ->
            categoriesFlow.emit(list)
        }
        val reference = references.getCategoryReference().also { ref ->
            ref.addValueEventListener(listener)
        }

        Pair(reference, listener)
    }
}