package com.wagnod.data.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.wagnod.data.AppDispatchers
import com.wagnod.domain.login.repository.AuthData
import com.wagnod.domain.login.repository.FirebaseRepository
import kotlinx.coroutines.withContext

class FirebaseRepositoryImpl(
    private val auth: FirebaseAuth,
    private val dispatchers: AppDispatchers
) : FirebaseRepository {

    override suspend fun signIn(authData: AuthData) {
        withContext(dispatchers.io) {
            auth.signInWithEmailAndPassword(authData.email, authData.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                    } else {
                        Log.w("Sign in", "signInWithEmail:failure", task.exception)
                    }
                }
        }
    }

    override suspend fun signUp(authData: AuthData) {
        withContext(dispatchers.io) {
            auth.createUserWithEmailAndPassword(authData.email, authData.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                    } else {
                        Log.w("Create User", "createUserWithEmail:failure", task.exception)
                    }
                }
        }
    }
}