package com.vald3nir.my_nanny.domain.navigation

import com.vald3nir.my_nanny.common.core.AppView

interface ScreenNavigation {
    fun redirectToLogin(view: AppView?)
    fun redirectToRegister(view: AppView?)
    fun redirectToDashboard(view: AppView?)
}