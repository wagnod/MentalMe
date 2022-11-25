package com.wagnod.domain.login.repository

interface FirebaseRepository {
    suspend fun signIn(authData: AuthData)
    suspend fun signUp(authData: AuthData)
}

data class AuthData(
    val email: String,
    val password: String
)