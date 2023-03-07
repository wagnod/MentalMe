package com.wagnod.core.extensions

import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.wagnod.core.datastore.user.UserInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun AuthResult.toUser() = UserInfo(
    id = user?.uid.orEmpty()
)

inline fun <reified T> createEventListener(
    crossinline callback: suspend (value: T) -> Unit
) = object : ValueEventListener {

    override fun onCancelled(p0: DatabaseError) {}

    override fun onDataChange(snapshot: DataSnapshot) {
        snapshot.getValue<T>().let { value ->
            if (value != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    callback.invoke(value)
                }
            } else {
                Log.d("Event Listener Error", "createEventListener(), value == null")
            }
        }
    }
}