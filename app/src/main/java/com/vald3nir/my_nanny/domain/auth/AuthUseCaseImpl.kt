package com.vald3nir.my_nanny.domain.auth

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.vald3nir.my_nanny.common.core.AppView
import com.vald3nir.my_nanny.data.repository.remote.auth.AuthRepository

class AuthUseCaseImpl(private val repository: AuthRepository) : AuthUseCase {

    override fun login(
        appView: AppView?,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit,
    ) {
        appView?.apply {
            getActivity().let { activity ->
                showLoading(true)
                repository.login(
                    activity = activity,
                    email = email,
                    password = password,
                    onSuccess = {
                        showLoading(false)
                        onSuccess.invoke()
                    },
                    onError = {
                        showLoading(false)
                        onError.invoke(it)
                    })
            }
        }
    }

    override suspend fun checkUserLogger(
        appView: AppView?,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        if (Firebase.auth.currentUser != null) {
            onSuccess.invoke()
        } else {
            onError.invoke()
        }
    }
}