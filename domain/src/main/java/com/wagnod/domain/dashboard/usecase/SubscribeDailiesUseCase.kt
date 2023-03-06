package com.wagnod.domain.dashboard.usecase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.wagnod.domain.UseCase
import com.wagnod.domain.dashboard.repository.DashboardRepository

class SubscribeDailiesUseCase(
    private val repository: DashboardRepository
) : UseCase<Unit, Pair<DatabaseReference, ValueEventListener>> {
    override suspend fun execute(input: Unit): Pair<DatabaseReference, ValueEventListener> {
        return repository.subscribeDailies()
    }
}