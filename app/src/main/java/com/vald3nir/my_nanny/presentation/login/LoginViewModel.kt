package com.vald3nir.my_nanny.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vald3nir.my_nanny.R
import com.vald3nir.my_nanny.common.core.BaseViewModel
import com.vald3nir.my_nanny.common.validations.isEmailValid
import com.vald3nir.my_nanny.common.validations.isPasswordValid
import com.vald3nir.my_nanny.domain.auth.AuthUseCase
import com.vald3nir.my_nanny.domain.navigation.ScreenNavigation

class LoginViewModel(
    private val screenNavigation: ScreenNavigation,
    private val authUseCase: AuthUseCase,
) : BaseViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    fun register() {
        screenNavigation.redirectToRegister(view)
    }

    fun login(email: String, password: String) {
        authUseCase.login(appView = view, email = email, password = password, onSuccess = {
            screenNavigation.redirectToDashboard(view)
        }, onError = {
            view?.showMessage(it?.message)
        })
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isEmailValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    data class LoginFormState(
        val usernameError: Int? = null,
        val passwordError: Int? = null,
        val isDataValid: Boolean = false
    )
}