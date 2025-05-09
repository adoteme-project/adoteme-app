package com.example.adoteme_app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.adoteme_app.auth.presentation.login_screen.LoginViewModel
import com.example.adoteme_app.interfaces.AuthApiService
import com.example.adoteme_app.network.RetrofitInstance
import org.koin.dsl.module

val ModuleAuth = module {

    single<AuthApiService> {
        RetrofitInstance.retrofit.create(AuthApiService::class.java)
    }

    viewModel {
        LoginViewModel(get())
    }
}

