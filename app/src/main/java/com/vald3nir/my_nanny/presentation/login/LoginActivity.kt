package com.vald3nir.my_nanny.presentation.login

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.vald3nir.my_nanny.common.extensions.afterTextChanged
import com.vald3nir.my_nanny.common.core.BaseActivity
import com.vald3nir.my_nanny.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.view = this
        setupObservers()
    }

    private fun setupObservers() {

        binding.apply {

            btnLogin.setOnClickListener { login() }
            btnRegister.setOnClickListener { register() }

            edtEmail.afterTextChanged {
                viewModel.loginDataChanged(
                    edtEmail.text.toString(),
                    edtPassword.text.toString()
                )
            }

            edtPassword.apply {
                afterTextChanged {
                    viewModel.loginDataChanged(
                        edtEmail.text.toString(),
                        edtPassword.text.toString()
                    )
                }

                setOnEditorActionListener { _, actionId, _ ->
                    when (actionId) {
                        EditorInfo.IME_ACTION_DONE ->
                            login()
                    }
                    false
                }
            }
        }

        viewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer
            binding.btnLogin.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                binding.edtEmail.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                binding.edtPassword.error = getString(loginState.passwordError)
            }
        })
    }

    private fun ActivityLoginBinding.login() {
        viewModel.login(
            email = edtEmail.text.toString(),
            password = edtPassword.text.toString()
        )
    }

    private fun ActivityLoginBinding.register() {
        viewModel.register()
    }
}
