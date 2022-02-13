package com.vald3nir.my_nanny.presentation.home

import android.os.Bundle
import android.widget.MediaController
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.vald3nir.my_nanny.R
import com.vald3nir.my_nanny.common.core.BaseActivity
import com.vald3nir.my_nanny.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.view = this
        initViews()
        setupObservers()
    }

    private fun initViews() {
        binding.apply {
            toolbar.apply {
                title.setText(R.string.app_name)
                btnBack.isVisible = false
                btnConfig.apply {
                    isVisible = true
                    setOnClickListener { viewModel.redirectToSettings() }
                }
            }
        }
    }

    private fun setupObservers() {
        viewModel.ipServer.observe(this@HomeActivity, Observer {
            setupVideoPlayer(ipServer = it ?: return@Observer)
        })
        viewModel.loadAppConfiguration()
    }

    private fun setupVideoPlayer(ipServer: String) {
        val mediaController = MediaController(this@HomeActivity)
        showLoading(true)
        binding.videoView.apply {
            mediaController.setAnchorView(this)
            setVideoPath(ipServer)
            setMediaController(mediaController)
            setOnPreparedListener { showLoading(false) }
            requestFocus()
            start()
        }
    }
}