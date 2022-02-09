package com.vald3nir.my_nanny.domain

import com.vald3nir.my_nanny.common.core.AppView

interface AuthUseCase {

    fun login(
        appView: AppView?,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit,
    )

    suspend fun checkUserLogger(
        appView: AppView?,
        onSuccess: () -> Unit,
        onError: () -> Unit,
    )
}