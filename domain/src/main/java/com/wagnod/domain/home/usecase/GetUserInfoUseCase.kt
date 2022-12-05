package com.wagnod.domain.home.usecase

import com.wagnod.domain.FirebaseDatabaseRepository
import com.wagnod.domain.UseCase
import com.wagnod.domain.UserInfo
import kotlinx.coroutines.flow.Flow

class GetUserInfoUseCase(
    private val repository: FirebaseDatabaseRepository
) : UseCase<Unit, Flow<UserInfo?>> {

    override suspend fun execute(input: Unit) = repository.getUserData()
}