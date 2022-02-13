package com.vald3nir.my_nanny.domain.navigation

import com.vald3nir.my_nanny.common.core.AppView

interface ScreenNavigation {
    fun redirectToLogin(appView: AppView?)
    fun redirectToRegister(appView: AppView?)
    fun redirectToHome(appView: AppView?)
    fun redirectToSettings(appView: AppView?)
}