package com.wagnod.domain.dashboard.usecase

import com.wagnod.domain.firebase.FirebaseDatabaseRepository
import com.wagnod.core.datastore.user.Goal
import com.wagnod.domain.UseCase

class PutGoalsToDatabaseUseCase(
    private val repository: FirebaseDatabaseRepository
) : UseCase<List<Goal>, Boolean> {

    override suspend fun execute(input: List<Goal>) = repository.createGoal(input)
}