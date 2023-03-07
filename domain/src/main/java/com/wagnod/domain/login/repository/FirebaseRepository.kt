package com.wagnod.domain.login.repository

import com.google.firebase.auth.FirebaseUser
import com.wagnod.core.datastore.user.UserInfo

interface FirebaseRepository {
    suspend fun getFirebaseUser() : FirebaseUser
    suspend fun signIn(authData: AuthData) : Boolean
    suspend fun signUp(authData: AuthData): UserInfo
    suspend fun isUserAuthorised() : Boolean
}

data class AuthData(
    val email: String,
    val password: String,
    val name: String
)