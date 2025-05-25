package com.example.adoteme_app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.adoteme_app.auth.presentation.login_screen.LoginViewModel
import com.example.adoteme_app.interfaces.AuthApiService
import com.example.adoteme_app.interfaces.RedefinirSenhaApiService
import com.example.adoteme_app.network.RetrofitInstance
import com.example.adoteme_app.presentation.viewmodel.NovaSenhaViewModel
import com.example.adoteme_app.presentation.viewmodel.RedefinicaoOptViewModel
import com.example.adoteme_app.presentation.viewmodel.RedefinicaoSenhaViewModel
import org.koin.dsl.module

val ModuleAuth = module {

    single<AuthApiService> {
        RetrofitInstance.retrofit.create(AuthApiService::class.java)
    }

    single<RedefinirSenhaApiService> {
        RetrofitInstance.retrofit.create(RedefinirSenhaApiService::class.java)
    }

    viewModel { LoginViewModel(get()) }
    viewModel { RedefinicaoSenhaViewModel(get()) }
    viewModel { RedefinicaoOptViewModel(get()) }
    viewModel { NovaSenhaViewModel(get()) }
}

