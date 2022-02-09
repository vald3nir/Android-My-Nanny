package com.vald3nir.my_nanny.data.repository.remote

import android.app.Activity


interface AuthRepository {

    fun login(
        activity: Activity,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit,
    )
}