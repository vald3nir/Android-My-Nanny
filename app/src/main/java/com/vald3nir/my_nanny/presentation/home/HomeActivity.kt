package com.vald3nir.my_nanny.presentation.home

import android.os.Bundle
import com.vald3nir.my_nanny.common.core.BaseActivity
import com.vald3nir.my_nanny.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        viewModel.view = this
//        setupObservers()


        binding.videoView.apply {

//            mediaController.setAnchorView(this@HomeActivity)
            setVideoPath("http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4")
//            setMediaController(mediaController)
            requestFocus()
            start()
        }
    }
}