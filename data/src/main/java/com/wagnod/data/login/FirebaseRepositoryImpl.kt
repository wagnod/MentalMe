package com.wagnod.data.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.wagnod.core.datastore.user.UserInfo
import com.wagnod.core.datastore.user.toMap
import com.wagnod.domain.AppDispatchers
import com.wagnod.domain.login.repository.AuthData
import com.wagnod.domain.login.repository.FirebaseRepository
import kotlinx.coroutines.tasks.asDeferred
import kotlinx.coroutines.withContext

class FirebaseRepositoryImpl(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase,
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
        val profileUpdates = userProfileChangeRequest {
            displayName = authData.name
        }
        user?.let {
            it.updateProfile(profileUpdates)
            createDatabaseUser(authData)
        }
        user != null
    }

    private suspend fun createDatabaseUser(authData: AuthData) = withContext(dispatchers.io) {
        val user = UserInfo(
            id = auth.currentUser?.uid ?: " ",
            userImage = "https://i.natgeofe.com/n/3861de2a-04e6-45fd-aec8-02e7809f9d4e/02-cat-training-NationalGeographic_1484324_square.jpg",
            name = authData.name,
            status = "Hello, world!"
        ).toMap()

        val childUpdates = hashMapOf<String, Any>(
            "/users/${auth.currentUser?.uid}" to user,
        )

        database.reference.updateChildren(childUpdates).isSuccessful
    }

    override suspend fun isUserAuthorised() = auth.currentUser != null
}