package com.vald3nir.my_nanny.data.repository.remote.auth

import android.app.Activity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthRepositoryImpl : AuthRepository {

    override fun login(
        activity: Activity,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit
    ) {
        Firebase.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity) {
            if (it.isSuccessful) {
                onSuccess.invoke()
            } else {
                onError.invoke(it.exception)
            }
        }
    }
}