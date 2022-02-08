package com.vald3nir.my_nanny.common.core

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vald3nir.my_nanny.R
import com.vald3nir.my_nanny.common.components.LoadingScreenDialog
import org.koin.ext.getFullName

open class BaseActivity : AppCompatActivity(), AppView {

    private var toast: Toast? = null
    private var loadingScreenDialog: LoadingScreenDialog? = null
    private val fragmentHashMap = LinkedHashMap<Any, BaseFragment>()
    private var currentFragment: BaseFragment? = null

    override fun getActivity(): Activity {
        return this
    }

    override fun showMessage(message: String?) {
        toast?.cancel()
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast?.show()
    }

    override fun showMessage(message: Int) {
        toast?.cancel()
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast?.show()
    }

    override fun showLoading(show: Boolean) {
        if (show) {
            loadingScreenDialog?.dismiss()
            loadingScreenDialog = LoadingScreenDialog(this)
            loadingScreenDialog?.show()
        } else {
            loadingScreenDialog?.dismiss()
        }
    }

    fun goToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitNow()
    }

    fun goBackToFragment(fragment: BaseFragment?) {
        if (!isFinishing) {
            supportFragmentManager.popBackStack(fragment?.let { it::class.getFullName() } ?: "", 0)
            currentFragment = fragmentHashMap[fragment?.let { it::class.getFullName() } ?: ""]
        }
    }
}