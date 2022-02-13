package com.vald3nir.my_nanny.presentation.config

import android.os.Bundle
import androidx.lifecycle.Observer
import com.vald3nir.my_nanny.R
import com.vald3nir.my_nanny.common.core.BaseActivity
import com.vald3nir.my_nanny.databinding.ActivitySettingsBinding
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity : BaseActivity() {

    private val viewModel: SettingsViewModel by viewModel()
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.view = this
        setupObservers()
        viewModel.loadConfiguration()
    }

    private fun setupObservers() {

        binding.apply {
            toolbar.apply {
                title.text = getString(R.string.configuration)
                btnBack.setOnClickListener { onBackPressed() }
                btnSave.setOnClickListener { updateConfiguration() }
            }
        }

        viewModel.appConfigForm.observe(this@SettingsActivity, Observer {
            val appConfigForm = it ?: return@Observer

            binding.apply {
                swAutoLogin.isChecked = appConfigForm.autoLogin
                edtIpServer.setText(appConfigForm.ipServer)
            }
        })
    }

    private fun updateConfiguration() {
        binding.apply {
            viewModel.updateConfiguration(
                autoLogin = swAutoLogin.isChecked,
                ipServer = edtIpServer.text.toString()
            )

        }
    }
}