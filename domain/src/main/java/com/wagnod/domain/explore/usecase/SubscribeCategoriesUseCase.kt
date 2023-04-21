package com.wagnod.domain.explore.usecase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.wagnod.domain.UseCase
import com.wagnod.domain.explore.repository.ExploreRepository

class SubscribeCategoriesUseCase(
    private val repository: ExploreRepository
) : UseCase<Unit, Pair<DatabaseReference, ValueEventListener>> {

    override suspend fun execute(input: Unit): Pair<DatabaseReference, ValueEventListener> {
        return repository.subscribeCategories()
    }
}