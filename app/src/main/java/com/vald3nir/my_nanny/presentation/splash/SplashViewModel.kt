package com.vald3nir.my_nanny.presentation.splash

import androidx.lifecycle.viewModelScope
import com.vald3nir.my_nanny.common.core.BaseViewModel
import com.vald3nir.my_nanny.domain.AppNavigation
import com.vald3nir.my_nanny.domain.AuthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(
    private val appNavigation: AppNavigation,
    private val authUseCase: AuthUseCase,
) : BaseViewModel() {

    fun checkUserLogger() {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCase.checkUserLogger(view,
                onSuccess = {
                    appNavigation.redirectToDashboard(view)
                }, onError = {
                    appNavigation.redirectToLogin(view)
                }
            )
        }
    }
}