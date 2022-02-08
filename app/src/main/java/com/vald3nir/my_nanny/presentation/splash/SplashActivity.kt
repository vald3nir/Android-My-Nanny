package com.vald3nir.my_nanny.presentation.splash

import android.content.Intent
import android.os.Bundle
import com.vald3nir.my_nanny.common.core.BaseActivity
import com.vald3nir.my_nanny.databinding.ActivitySplashBinding
import com.vald3nir.my_nanny.presentation.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
//        viewModel.setView(this)
        binding.apply {
            loading.showLoadingAnimation()
        }
        startActivity(Intent(this, LoginActivity::class.java))
    }


}