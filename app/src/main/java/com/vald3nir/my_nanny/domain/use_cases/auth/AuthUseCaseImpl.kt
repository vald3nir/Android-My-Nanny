package com.vald3nir.my_nanny.domain.use_cases.auth

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
                repository.login(
                    activity = activity,
                    email = email,
                    password = password,
                    onSuccess = onSuccess,
                    onError = onError
                )
            }
        }
    }
}