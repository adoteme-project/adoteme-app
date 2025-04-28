package com.example.adoteme_app.di

import com.example.adoteme_app.interfaces.OngApiService
import com.example.adoteme_app.network.RetrofitInstance
import com.example.adoteme_app.pets.presentation.ongs_screen.OngViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ModuleOngs = module {

    single {
        RetrofitInstance.retrofit.create(OngApiService::class.java)
    }

    viewModel {
        OngViewModel(get())
    }
}
