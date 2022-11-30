package com.wagnod.domain.login.repository

interface FirebaseRepository {
    suspend fun signIn(authData: AuthData) : Boolean
    suspend fun signUp(authData: AuthData) : Boolean
    suspend fun isUserAuthorised() : Boolean
}

data class AuthData(
    val email: String,
    val password: String
)