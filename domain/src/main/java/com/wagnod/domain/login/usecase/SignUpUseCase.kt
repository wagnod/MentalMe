package com.wagnod.domain.login.usecase

import com.wagnod.domain.UseCase
import com.wagnod.domain.login.repository.AuthData
import com.wagnod.domain.login.repository.FirebaseRepository

class SignUpUseCase(
    private val repository: FirebaseRepository
) : UseCase<AuthData, Unit> {

    override suspend fun execute(input: AuthData) {
        repository.signUp(input)
    }
}