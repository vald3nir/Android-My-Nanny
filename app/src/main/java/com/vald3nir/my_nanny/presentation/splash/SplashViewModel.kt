package com.vald3nir.my_nanny.presentation.splash

import com.vald3nir.my_nanny.common.core.BaseViewModel
import com.vald3nir.my_nanny.domain.use_cases.config.AppConfigUseCase
import com.vald3nir.my_nanny.domain.navigation.ScreenNavigation
import java.util.*
import kotlin.concurrent.schedule

class SplashViewModel(
    private val screenNavigation: ScreenNavigation,
    private val appConfigUseCase: AppConfigUseCase
) : BaseViewModel() {

    fun loadAppConfig() {
        Timer("check app configuration", false).schedule(1500) {
            appConfigUseCase.loadConfiguration(view,
                onSuccess = {
                    if (it?.autoLogin == true) {
                        screenNavigation.redirectToHome(view)
                    } else {
                        screenNavigation.redirectToLogin(view)
                    }
                },
                onError = {
                    view?.showMessage(it?.message)
                }
            )
        }
    }
}