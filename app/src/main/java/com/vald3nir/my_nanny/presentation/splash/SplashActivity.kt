package com.vald3nir.my_nanny.presentation.splash

import android.content.Intent
import android.os.Bundle
import com.vald3nir.my_nanny.common.base_views.BaseActivity
import com.vald3nir.my_nanny.databinding.ActivitySplashBinding
import com.vald3nir.my_nanny.presentation.login.LoginActivity

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.apply {
            loading.showLoadingAnimation()
        }

        startActivity(Intent(this, LoginActivity::class.java))
    }
}