package com.vald3nir.my_nanny.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vald3nir.my_nanny.common.core.BaseViewModel
import com.vald3nir.my_nanny.domain.navigation.ScreenNavigation
import com.vald3nir.my_nanny.domain.use_cases.config.AppConfigUseCase

class HomeViewModel(
    private val screenNavigation: ScreenNavigation,
    private val appConfigUseCase: AppConfigUseCase,
) : BaseViewModel() {

    private val _ipServer = MutableLiveData<String?>()
    val ipServer: LiveData<String?> = _ipServer

    fun redirectToSettings() {
        screenNavigation.redirectToSettings(view)
    }

    fun loadAppConfiguration() {
        view?.showLoading(true)
        appConfigUseCase.loadConfiguration(
            view,
            onSuccess = {
                view?.showLoading(false)
                var ipServer: String? =
                    "http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"
                if (!it?.ipServer.isNullOrBlank()) {
                    ipServer = it?.ipServer
                }
                _ipServer.postValue(ipServer)
            },
            onError = {
                view?.showLoading(false)
                view?.showMessage(it?.message)
            }
        )
    }


}