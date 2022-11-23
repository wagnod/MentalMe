package com.wagnod.login

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccountServiceImpl : AccountService {
    override fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit) {
        val auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Create User", "createUserWithEmail:success")
                    val user = auth.currentUser
                } else {
                    Log.w("Create User", "createUserWithEmail:failure", task.exception)
                }
            }
    }
}