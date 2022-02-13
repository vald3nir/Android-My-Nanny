package com.vald3nir.my_nanny.presentation.config

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vald3nir.my_nanny.common.core.BaseViewModel
import com.vald3nir.my_nanny.data.dto.AppConfigDTO
import com.vald3nir.my_nanny.domain.use_cases.config.AppConfigUseCase

class SettingsViewModel(
    private val appConfigUseCase: AppConfigUseCase
) : BaseViewModel() {

    private val _appConfigForm = MutableLiveData<AppConfigDTO>()
    val appConfigForm: LiveData<AppConfigDTO> = _appConfigForm

    fun loadConfiguration() {
        view?.showLoading(true)
        appConfigUseCase.loadConfiguration(
            view,
            onSuccess = {
                view?.showLoading(false)
                _appConfigForm.postValue(it)
            },
            onError = {
                view?.showLoading(false)
                view?.showMessage(it?.message)
            }
        )
    }

    fun updateConfiguration(
        autoLogin: Boolean,
        ipServer: String,
    ) {
        view?.showLoading(true)
        appConfigUseCase.updateConfiguration(
            view, AppConfigDTO(autoLogin, ipServer),
            onSuccess = {
                view?.showLoading(false)
                view?.getActivity()?.finish()
            },
            onError = {
                view?.showLoading(false)
                view?.showMessage(it?.message)
            }
        )
    }
}