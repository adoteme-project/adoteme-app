package com.example.adoteme_app.di

import com.example.adoteme_app.interfaces.AdotanteApiService
import com.example.adoteme_app.interfaces.AnimalApiService
import com.example.adoteme_app.model.AdotanteViewModel
import com.example.adoteme_app.network.RetrofitInstance
import com.example.adoteme_app.pets.presentation.pets_screen.AnimalViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ModulePets = module {

    single {
        RetrofitInstance.retrofit.create(AnimalApiService::class.java)
    }

    single {
        RetrofitInstance.retrofit.create(AdotanteApiService::class.java)
    }

    viewModel {
        AnimalViewModel(get())
    }

    viewModel {
        AdotanteViewModel(get())
    }
}
