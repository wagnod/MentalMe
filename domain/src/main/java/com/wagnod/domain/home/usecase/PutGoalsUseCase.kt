package com.wagnod.domain.home.usecase

import com.wagnod.domain.FirebaseDatabaseRepository
import com.wagnod.domain.Goal
import com.wagnod.domain.UseCase

class PutGoalsUseCase(
    private val repository: FirebaseDatabaseRepository
) : UseCase<List<Goal>, Boolean> {

    override suspend fun execute(input: List<Goal>) = repository.createGoal(input)
}