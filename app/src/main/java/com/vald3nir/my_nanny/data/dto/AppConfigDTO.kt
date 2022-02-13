package com.vald3nir.my_nanny.data.dto

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class AppConfigDTO(
    val autoLogin: Boolean = false,
    val ipServer: String? = null
) {
    constructor() : this(false, null) {}

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "autoLogin" to autoLogin,
            "ipServer" to ipServer,
        )
    }
}