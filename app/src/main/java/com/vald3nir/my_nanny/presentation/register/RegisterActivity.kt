package com.vald3nir.my_nanny.presentation.register

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.vald3nir.my_nanny.common.core.BaseActivity
import com.vald3nir.my_nanny.common.extensions.afterTextChanged
import com.vald3nir.my_nanny.databinding.ActivityRegisterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : BaseActivity() {

    private val viewModel: RegisterViewModel by viewModel()
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.view = this
        setupObservers()
    }

    private fun setupObservers() {

        binding.apply {

            btnBack.setOnClickListener { onBackPressed() }
            btnRegister.setOnClickListener { register() }

            edtEmail.afterTextChanged { registerDataChanged() }

            edtPassword.apply { afterTextChanged { registerDataChanged() } }

            edtConfirmPassword.apply {
                afterTextChanged { registerDataChanged() }
                setOnEditorActionListener { _, actionId, _ ->
                    when (actionId) {
                        EditorInfo.IME_ACTION_DONE ->
                            register()
                    }
                    false
                }
            }
        }

        viewModel.registerFormState.observe(this@RegisterActivity, Observer {
            val loginState = it ?: return@Observer
            binding.btnRegister.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                binding.edtEmail.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                binding.edtPassword.error = getString(loginState.passwordError)
            }
            if (loginState.passwordNotEquals != null) {
                binding.edtConfirmPassword.error = getString(loginState.passwordNotEquals)
            }
        })
    }

    private fun ActivityRegisterBinding.registerDataChanged() {
        viewModel.registerDataChanged(
            edtEmail.text.toString(),
            edtPassword.text.toString(),
            edtConfirmPassword.text.toString()
        )
    }

    private fun ActivityRegisterBinding.register() {
        viewModel.register(
            email = edtEmail.text.toString(),
            password = edtPassword.text.toString()
        )
    }
}
