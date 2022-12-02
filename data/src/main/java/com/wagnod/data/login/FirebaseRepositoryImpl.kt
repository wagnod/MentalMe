package com.wagnod.data.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.wagnod.data.AppDispatchers
import com.wagnod.domain.UserInfo
import com.wagnod.domain.login.repository.AuthData
import com.wagnod.domain.login.repository.FirebaseRepository
import com.wagnod.domain.toMap
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

    override suspend fun getUserInfo() = withContext(dispatchers.io) {
        val path = auth.currentUser?.uid ?: ""
        val user = database.reference.child("users").child(path).get()
            .asDeferred()
            .await()
            .getValue<UserInfo>()
        user ?: throw IllegalStateException("getUserInfo() wrong state")
    }
}