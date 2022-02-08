package com.vald3nir.my_nanny.domain

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.vald3nir.my_nanny.common.core.AppView

class AuthUseCaseImpl : AuthUseCase {

    override fun login(
        appView: AppView?,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: () -> Unit,
    ) {
        appView?.apply {
            getActivity().let { activity ->
                showLoading(true)
                Firebase.auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity) {
                        showLoading(false)
                        if (it.isSuccessful) {
                            onSuccess.invoke()
                        } else {
                            onError.invoke()
                        }
                    }
            }
        }
    }
}