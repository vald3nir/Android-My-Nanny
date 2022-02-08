package com.vald3nir.my_nanny.common.core

import android.os.Bundle

interface FragmentFactory {

    fun createFragment(tab: Any): BaseFragment?

    fun createFragment(tab: Any, arguments: Bundle?): BaseFragment? {
        val fragment = createFragment(tab)
        fragment?.arguments = arguments
        return fragment
    }
}