package com.wagnod.domain.home.usecase

import com.wagnod.domain.UseCase
import com.wagnod.domain.UserInfo
import com.wagnod.domain.login.repository.FirebaseRepository

class GetUserInfoUseCase(
    private val repository: FirebaseRepository
) : UseCase<Unit, UserInfo> {

    override suspend fun execute(input: Unit) = repository.getUserInfo()
}