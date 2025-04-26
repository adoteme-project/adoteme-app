package com.example.adoteme_app.di

import com.example.adoteme_app.auth.presentation.register_screen.RegistrationViewModel
import com.example.adoteme_app.interfaces.Adotante.AdotanteApiService
import com.example.adoteme_app.interfaces.AuthApiService
import com.example.adoteme_app.network.RetrofitInstance
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ModuleAdotante = module{

    single {
        RetrofitInstance.retrofit.create(AdotanteApiService::class.java)
    }

    factory<AuthApiService> {
        RetrofitInstance.retrofit.create(AuthApiService::class.java)
    }

    viewModel(){
        RegistrationViewModel(get())
    }
}
