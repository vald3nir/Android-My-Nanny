package com.vald3nir.my_nanny.common.core

import android.app.Activity

interface AppView {

    fun getActivity(): Activity

    fun showMessage(message: String?)

    fun showMessage(message: Int)

    fun showLoading(show: Boolean)

}