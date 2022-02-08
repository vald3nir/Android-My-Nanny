package com.vald3nir.my_nanny.di

import com.vald3nir.my_nanny.domain.AuthUseCase
import com.vald3nir.my_nanny.domain.AuthUseCaseImpl
import com.vald3nir.my_nanny.presentation.login.LoginViewModel
import com.vald3nir.my_nanny.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModule {

    fun get(): Module {

        return module {

            viewModel { SplashViewModel() }
            viewModel { LoginViewModel(authUseCase = get()) }

            factory<AuthUseCase> { AuthUseCaseImpl() }
        }
    }
}