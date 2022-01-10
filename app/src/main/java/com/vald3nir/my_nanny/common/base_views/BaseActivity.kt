package com.vald3nir.my_nanny.common.base_views

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vald3nir.my_nanny.common.components.LoadingScreenDialog

open class BaseActivity : AppCompatActivity() {

    private var toast: Toast? = null
    private var loadingScreenDialog: LoadingScreenDialog? = null

    fun showMessage(message: String?) {
        toast?.cancel()
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast?.show()
    }

    fun showMessage(message: Int) {
        toast?.cancel()
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast?.show()
    }

    fun showLoadingAnimation() {
        loadingScreenDialog?.dismiss()
        loadingScreenDialog = LoadingScreenDialog(this)
        loadingScreenDialog?.show()
    }
}