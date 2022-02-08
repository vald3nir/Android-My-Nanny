package com.vald3nir.my_nanny.common.core

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    private var baseActivity: BaseActivity? = null

    fun setView(baseActivity: BaseActivity) {
        this.baseActivity = baseActivity
    }
}