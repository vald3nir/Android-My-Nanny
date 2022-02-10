package com.vald3nir.my_nanny.domain.navigation

import android.content.Intent
import com.vald3nir.my_nanny.common.core.AppView
import com.vald3nir.my_nanny.presentation.home.HomeActivity
import com.vald3nir.my_nanny.presentation.login.LoginActivity
import com.vald3nir.my_nanny.presentation.register.RegisterActivity

class ScreenNavigationImpl() : ScreenNavigation {

    private fun <T> openActivity(view: AppView?, classJava: Class<T>) {
        view?.getActivity()?.apply {
            startActivity(
                Intent(this, classJava)
            )
        }
    }

    override fun redirectToLogin(view: AppView?) {
        openActivity(view, LoginActivity::class.java)
    }

    override fun redirectToRegister(view: AppView?) {
        openActivity(view, RegisterActivity::class.java)
    }

    override fun redirectToHome(view: AppView?) {
        openActivity(view, HomeActivity::class.java)
    }
}