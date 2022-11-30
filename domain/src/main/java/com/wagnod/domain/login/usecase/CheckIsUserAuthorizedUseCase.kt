package com.wagnod.domain.login.usecase

import com.wagnod.domain.UseCase
import com.wagnod.domain.login.repository.FirebaseRepository

class CheckIsUserAuthorizedUseCase(
    private val repository: FirebaseRepository
) : UseCase<Unit, Boolean> {

    override suspend fun execute(input: Unit): Boolean = repository.isUserAuthorised()
}