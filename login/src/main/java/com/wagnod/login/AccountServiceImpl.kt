package com.wagnod.login

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wagnod.core_ui.Navigator

class AccountServiceImpl : AccountService {
    val auth = Firebase.auth

    override fun authenticate(email: String, password: String, navigator: Navigator?) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                navigator?.navigateToHome()
            } else {
                Log.w("Sign in", "signInWithEmail:failure", task.exception)
            }
        }
    }

    override fun linkAccount(email: String, password: String, navigator: Navigator?) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navigator?.navigateToHome()
                } else {
                    Log.w("Create User", "createUserWithEmail:failure", task.exception)
                }
            }
    }
}