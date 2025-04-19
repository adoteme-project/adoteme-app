package com.example.adoteme_app.di

import com.example.adoteme_app.interfaces.AnimalApiService
import com.example.adoteme_app.network.RetrofitInstance
import com.example.adoteme_app.pets.presentation.pets_screen.AnimalViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ModulePets = module {

    single<AnimalApiService> {
        RetrofitInstance.retrofit.create(AnimalApiService::class.java)
    }

    viewModel {
        AnimalViewModel(get())
    }
}
