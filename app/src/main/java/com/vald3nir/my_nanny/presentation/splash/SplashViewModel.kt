package com.vald3nir.my_nanny.presentation.splash

import androidx.lifecycle.viewModelScope
import com.vald3nir.my_nanny.common.core.BaseViewModel
import com.vald3nir.my_nanny.domain.auth.AuthUseCase
import com.vald3nir.my_nanny.domain.navigation.ScreenNavigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule


class SplashViewModel(
    private val screenNavigation: ScreenNavigation,
    private val authUseCase: AuthUseCase,
) : BaseViewModel() {

    fun checkUserLogger() {

        Timer("checkUserLogger", false).schedule(2000) {
            viewModelScope.launch(Dispatchers.IO) {
                authUseCase.checkUserLogger(view,
                    onSuccess = {
                        screenNavigation.redirectToDashboard(view)
                    }, onError = {
                        screenNavigation.redirectToLogin(view)
                    }
                )
            }
        }
    }
}