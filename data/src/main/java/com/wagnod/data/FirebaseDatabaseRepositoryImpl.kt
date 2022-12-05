package com.wagnod.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.wagnod.domain.FirebaseDatabaseRepository
import com.wagnod.domain.UserInfo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow

class FirebaseDatabaseRepositoryImpl(
    database: FirebaseDatabase,
    auth: FirebaseAuth
) : FirebaseDatabaseRepository {

    private val userReference = database.getReference("$USERS_REFERENCE/${auth.currentUser?.uid}")

    override fun getUserData() = callbackFlow {
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

    private companion object {
        const val USERS_REFERENCE = "users"
    }
}