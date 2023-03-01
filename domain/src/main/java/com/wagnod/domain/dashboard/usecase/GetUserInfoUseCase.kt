package com.wagnod.domain.dashboard.usecase

import com.wagnod.core.datastore.user.UserInfo
import com.wagnod.domain.firebase.FirebaseDatabaseRepository
import com.wagnod.domain.UseCase
import kotlinx.coroutines.flow.Flow

class GetUserInfoUseCase(
    private val repository: FirebaseDatabaseRepository
) : UseCase<Unit, Flow<UserInfo?>> {

    override suspend fun execute(input: Unit) = repository.getUserData()
}