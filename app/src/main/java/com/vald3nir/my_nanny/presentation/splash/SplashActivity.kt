package com.vald3nir.my_nanny.presentation.splash

import android.os.Bundle
import com.vald3nir.my_nanny.common.base_views.BaseActivity
import com.vald3nir.my_nanny.databinding.ActivitySplashBinding

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
    }


}