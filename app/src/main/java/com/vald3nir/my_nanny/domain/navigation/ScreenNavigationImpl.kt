package com.vald3nir.my_nanny.domain.navigation

import android.content.Intent
import com.vald3nir.my_nanny.common.core.AppView
import com.vald3nir.my_nanny.presentation.config.SettingsActivity
import com.vald3nir.my_nanny.presentation.home.HomeActivity
import com.vald3nir.my_nanny.presentation.login.LoginActivity
import com.vald3nir.my_nanny.presentation.register.RegisterActivity

class ScreenNavigationImpl : ScreenNavigation {

    private fun <T> openActivity(view: AppView?, classJava: Class<T>, newStack: Boolean = false) {
        view?.getActivity()?.apply {
            val newIntent = Intent(this, classJava)
            if (newStack) {
                newIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(newIntent)
        }
    }

    override fun redirectToLogin(appView: AppView?) {
        openActivity(appView, LoginActivity::class.java, newStack = true)
    }

    override fun redirectToRegister(appView: AppView?) {
        openActivity(appView, RegisterActivity::class.java)
    }

    override fun redirectToHome(appView: AppView?) {
        openActivity(appView, HomeActivity::class.java, newStack = true)
    }

    override fun redirectToSettings(appView: AppView?) {
        openActivity(appView, SettingsActivity::class.java)
    }
}