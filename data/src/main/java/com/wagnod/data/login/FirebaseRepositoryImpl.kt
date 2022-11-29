package com.wagnod.data.login

import com.google.firebase.auth.FirebaseAuth
import com.wagnod.data.AppDispatchers
import com.wagnod.domain.login.repository.AuthData
import com.wagnod.domain.login.repository.FirebaseRepository
import kotlinx.coroutines.tasks.asDeferred
import kotlinx.coroutines.withContext

class FirebaseRepositoryImpl(
    private val auth: FirebaseAuth,
    private val dispatchers: AppDispatchers
) : FirebaseRepository {

    override suspend fun signIn(authData: AuthData) = withContext(dispatchers.io) {
        val user = auth.signInWithEmailAndPassword(authData.email, authData.password)
            .asDeferred()
            .await()
            .user
        user != null
    }

    override suspend fun signUp(authData: AuthData) = withContext(dispatchers.io) {
        val user = auth.createUserWithEmailAndPassword(authData.email, authData.password)
            .asDeferred()
            .await()
            .user
        user != null
    }
}