package com.vald3nir.my_nanny.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vald3nir.my_nanny.R
import com.vald3nir.my_nanny.common.core.BaseViewModel
import com.vald3nir.my_nanny.common.validations.isEmailValid
import com.vald3nir.my_nanny.common.validations.isPasswordValid
import com.vald3nir.my_nanny.domain.navigation.ScreenNavigation
import com.vald3nir.my_nanny.domain.register.RegisterUseCase

class RegisterViewModel(
    private val screenNavigation: ScreenNavigation,
    private val registerUseCase: RegisterUseCase,
) : BaseViewModel() {

    private val _loginForm = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> = _loginForm

    fun register(email: String, password: String) {
        registerUseCase.registerNewUser(
            appView = view,
            email = email,
            password = password,
            onSuccess = {
                screenNavigation.redirectToDashboard(view)
            },
            onError = {
                view?.showMessage(it?.message)
            })
    }

    fun registerDataChanged(email: String, password: String, confirmPassword: String) {
        if (!isEmailValid(email)) {
            _loginForm.value = RegisterFormState(usernameError = R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = RegisterFormState(passwordError = R.string.invalid_password)
        } else if (password != confirmPassword) {
            _loginForm.value = RegisterFormState(passwordNotEquals = R.string.passwords_not_equals)
        } else {
            _loginForm.value = RegisterFormState(isDataValid = true)
        }
    }

    data class RegisterFormState(
        val usernameError: Int? = null,
        val passwordError: Int? = null,
        val passwordNotEquals: Int? = null,
        val isDataValid: Boolean = false
    )
}