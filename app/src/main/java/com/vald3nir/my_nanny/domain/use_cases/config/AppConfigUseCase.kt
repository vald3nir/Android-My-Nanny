package com.vald3nir.my_nanny.domain.use_cases.config

import com.vald3nir.my_nanny.common.core.AppView
import com.vald3nir.my_nanny.data.dto.AppConfigDTO

interface AppConfigUseCase {

    fun loadConfiguration(
        appView: AppView?,
        onSuccess: (AppConfigDTO?) -> Unit,
        onError: (e: Exception?) -> Unit,
    )

    fun updateConfiguration(
        appView: AppView?,
        appConfigDTO: AppConfigDTO,
        onSuccess: () -> Unit,
        onError: (e: Exception?) -> Unit,
    )
}