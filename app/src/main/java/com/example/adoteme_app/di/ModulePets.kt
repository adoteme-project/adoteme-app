package com.example.adoteme_app.di

import com.example.adoteme_app.data.repository.PerfilRepository
import com.example.adoteme_app.interfaces.AdotanteApiService
import com.example.adoteme_app.interfaces.AnimalApiService
import com.example.adoteme_app.model.PerfilViewModel
import com.example.adoteme_app.interfaces.RequisicaoApiService
import com.example.adoteme_app.model.AdotanteViewModel
import com.example.adoteme_app.network.RetrofitInstance
import com.example.adoteme_app.perfil.data.use_case.PerfilUseCase
import com.example.adoteme_app.pets.presentation.favoritos_screen.AnimalFavoritoViewModel
import com.example.adoteme_app.pets.presentation.ongs_screen.OngViewModel
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

    single { PerfilRepository(get()) }
    single { PerfilUseCase(get()) }

    viewModel {
        AnimalViewModel(get())
    }

    viewModel {
        AnimalFavoritoViewModel(get())
    }

    viewModel {
        PerfilViewModel(get())
    }

    viewModel {
        AdotanteViewModel(get())
    }
}
