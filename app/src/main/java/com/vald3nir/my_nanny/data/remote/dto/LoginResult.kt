package com.vald3nir.my_nanny.data.remote.dto

import com.vald3nir.my_nanny.presentation.login.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)