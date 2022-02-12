package com.vald3nir.my_nanny.presentation.config

import android.os.Bundle
import com.vald3nir.my_nanny.R
import com.vald3nir.my_nanny.common.core.BaseActivity
import com.vald3nir.my_nanny.databinding.SettingsActivityBinding
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsActivity : BaseActivity() {

    private val viewModel: SettingsViewModel by viewModel()
    private lateinit var binding: SettingsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.view = this
        setupObservers()
    }

    private fun setupObservers() {
        binding.apply {
            toolbar.apply {
                title.text = getString(R.string.configuration)
                btnBack.setOnClickListener { onBackPressed() }
            }
        }
    }


}