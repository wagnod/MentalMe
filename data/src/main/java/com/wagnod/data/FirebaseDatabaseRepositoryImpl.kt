package com.wagnod.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.wagnod.domain.FirebaseDatabaseRepository
import com.wagnod.domain.Goal
import com.wagnod.domain.UserInfo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext

class FirebaseDatabaseRepositoryImpl(
    private val database: FirebaseDatabase,
    private val auth: FirebaseAuth,
    private val dispatchers: AppDispatchers
) : FirebaseDatabaseRepository {

    private val userReference = database.getReference("$USERS_REFERENCE/${auth.currentUser?.uid}")

    override suspend fun getUserData() = callbackFlow {
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userInfo = dataSnapshot.getValue(UserInfo::class.java)
                trySendBlocking(userInfo)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Zhopa", "getUserData ${error.message}")
            }
        }

        userReference.addValueEventListener(valueEventListener)
        awaitClose {
            userReference.removeEventListener(valueEventListener)
        }
    }

    override suspend fun createGoal(goals: List<Goal>) = withContext(dispatchers.io) {
        val childUpdates = hashMapOf<String, Any>(
            "$USERS_REFERENCE/${auth.currentUser?.uid}/goals" to goals,
        )
        database.reference.updateChildren(childUpdates).isSuccessful
    }


    private companion object {
        const val USERS_REFERENCE = "users"
    }
}