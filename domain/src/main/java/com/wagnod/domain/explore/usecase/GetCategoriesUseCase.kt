package com.wagnod.domain.explore.usecase

import com.wagnod.core.datastore.articles.Category
import com.wagnod.domain.UseCase
import com.wagnod.domain.explore.repository.ExploreRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(
    private val repository: ExploreRepository
) : UseCase<Unit, Flow<List<Category>>> {

    override suspend fun execute(input: Unit) = repository.getCategories()
}