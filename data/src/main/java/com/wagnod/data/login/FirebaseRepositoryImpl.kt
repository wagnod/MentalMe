package com.wagnod.data.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.wagnod.core.datastore.user.UserInfo
import com.wagnod.core.datastore.user.toMap
import com.wagnod.core.extensions.toUser
import com.wagnod.domain.AppDispatchers
import com.wagnod.domain.firebase.FirebaseDatabaseReferences
import com.wagnod.domain.login.repository.AuthData
import com.wagnod.domain.login.repository.FirebaseRepository
import kotlinx.coroutines.tasks.asDeferred
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirebaseRepositoryImpl(
    private val auth: FirebaseAuth,
    private val dispatchers: AppDispatchers,
    private val references: FirebaseDatabaseReferences
) : FirebaseRepository {

    override suspend fun getFirebaseUser(): FirebaseUser {
        return auth.currentUser ?: throw IllegalStateException("User expected")
    }

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
            .toUser()
        createDatabaseUser(user)
    }

    private suspend fun createDatabaseUser(user: UserInfo) = withContext(dispatchers.io) {
        val firebaseUser = getFirebaseUser()
        references.getUserReference(firebaseUser.uid).setValue(user.toMap()).await()
        user
    }

    override suspend fun isUserAuthorised() = auth.currentUser != null
}