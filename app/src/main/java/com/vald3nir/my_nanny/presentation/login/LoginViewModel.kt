package com.vald3nir.my_nanny.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vald3nir.my_nanny.R
import com.vald3nir.my_nanny.common.core.BaseViewModel
import com.vald3nir.my_nanny.common.validations.isPasswordValid
import com.vald3nir.my_nanny.common.validations.isEmailValid
import com.vald3nir.my_nanny.data.remote.dto.LoginFormState
import com.vald3nir.my_nanny.data.remote.dto.LoginResult
import com.vald3nir.my_nanny.domain.AuthUseCase

class LoginViewModel(
    private val authUseCase: AuthUseCase
) : BaseViewModel() {


    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(email: String, password: String) {
        authUseCase.login(appView = view, email = email, password = password, onSuccess = {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = "deu certo"))
        }, onError = {
            _loginResult.value = LoginResult(error = R.string.login_failed)
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
}