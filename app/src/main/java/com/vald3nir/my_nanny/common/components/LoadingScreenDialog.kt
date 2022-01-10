package com.vald3nir.my_nanny.common.components

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.vald3nir.my_nanny.R
import com.vald3nir.my_nanny.databinding.CustomLoadingScreenBinding

class LoadingScreenDialog(context: Context) :
    Dialog(context, R.style.full_screen_dialog) {

    init {
        setCancelable(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val binding = CustomLoadingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            loading.showLoadingAnimation()
            loading.setOnClickListener {
                dismiss()
            }
        }

    }


}