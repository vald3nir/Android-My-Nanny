package com.vald3nir.my_nanny.domain.use_cases.register

import com.vald3nir.my_nanny.common.core.AppView

interface RegisterUseCase {

    fun registerNewUser(
        appView: AppView?,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit,
    )
}