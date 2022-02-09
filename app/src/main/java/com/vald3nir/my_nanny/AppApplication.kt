package com.vald3nir.my_nanny

import android.app.Application
import com.vald3nir.my_nanny.data.repository.remote.auth.AuthRepository
import com.vald3nir.my_nanny.data.repository.remote.auth.AuthRepositoryImpl
import com.vald3nir.my_nanny.data.repository.remote.register.RegisterRepository
import com.vald3nir.my_nanny.data.repository.remote.register.RegisterRepositoryImpl
import com.vald3nir.my_nanny.domain.auth.AuthUseCase
import com.vald3nir.my_nanny.domain.auth.AuthUseCaseImpl
import com.vald3nir.my_nanny.domain.navigation.ScreenNavigation
import com.vald3nir.my_nanny.domain.navigation.ScreenNavigationImpl
import com.vald3nir.my_nanny.domain.register.RegisterUseCase
import com.vald3nir.my_nanny.domain.register.RegisterUseCaseImpl
import com.vald3nir.my_nanny.presentation.login.LoginViewModel
import com.vald3nir.my_nanny.presentation.register.RegisterViewModel
import com.vald3nir.my_nanny.presentation.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.module

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@AppApplication)
            modules(getModules())
        }
    }

    private fun getModules(): Module {

        return module {

            viewModel { SplashViewModel(screenNavigation = get(), authUseCase = get()) }
            viewModel { LoginViewModel(screenNavigation = get(), authUseCase = get()) }
            viewModel { RegisterViewModel(screenNavigation = get(), registerUseCase = get()) }

            factory<AuthRepository> { AuthRepositoryImpl() }
            factory<AuthUseCase> { AuthUseCaseImpl(repository = get()) }

            factory<RegisterRepository> { RegisterRepositoryImpl() }
            factory<RegisterUseCase> { RegisterUseCaseImpl(repository = get()) }

            factory<ScreenNavigation> { ScreenNavigationImpl() }
        }
    }
}
