package com.example.adoteme_app.auth.presentation.login_screen.module_auth

import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.adoteme_app.auth.presentation.login_screen.viewModel.LoginViewModel
import com.example.adoteme_app.auth.presentation.login_screen.auth_service.AuthApi
import com.example.adoteme_app.auth.presentation.login_screen.auth_service.AuthApiService
import org.koin.dsl.module

val ModuleAuth = module {

    // AuthApi não precisa de token, então é uma instância simples
    factory<AuthApiService> {
        AuthApi.getApi();
    }

    // ViewModel de login
    viewModel{
        LoginViewModel(get())
    }
}
