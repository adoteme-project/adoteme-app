package com.example.adoteme_app.application.di

import com.example.adoteme_app.application.config.Configuracoes
import com.example.adoteme_app.presentation.viewmodel.LoginViewModel
import com.example.adoteme_app.data.repository.PerfilRepository
import com.example.adoteme_app.data.network.api.AdotanteApiService
import com.example.adoteme_app.data.network.api.AnimalApiService
import com.example.adoteme_app.data.network.api.AuthApiService
import com.example.adoteme_app.data.network.api.OngApiService
import com.example.adoteme_app.data.network.api.RedefinirSenhaApiService
import com.example.adoteme_app.data.network.api.RequisicaoApiService
import com.example.adoteme_app.presentation.viewmodel.AdotanteViewModel
import com.example.adoteme_app.presentation.viewmodel.PerfilViewModel
import com.example.adoteme_app.domain.usecase.PerfilUseCase
import com.example.adoteme_app.presentation.viewmodel.AnimalFavoritoViewModel
import com.example.adoteme_app.presentation.viewmodel.OngViewModel
import com.example.adoteme_app.presentation.viewmodel.RequisicaoViewModel
import com.example.adoteme_app.presentation.viewmodel.AnimalViewModel
import com.example.adoteme_app.presentation.viewmodel.NovaSenhaViewModel
import com.example.adoteme_app.presentation.viewmodel.RedefinicaoOptViewModel
import com.example.adoteme_app.presentation.viewmodel.RedefinicaoSenhaViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val moduloPrincipal = module {

    single<Retrofit> {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val clienteHttp = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(Configuracoes.BASE_URL)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    factory <AuthApiService> {
        get<Retrofit>().create(AuthApiService::class.java)
    }

    factory<RedefinirSenhaApiService> {
        get<Retrofit>().create(RedefinirSenhaApiService::class.java)
    }

    factory<OngApiService> {
        get<Retrofit>().create(OngApiService::class.java)
    }

    factory<AnimalApiService> {
        get<Retrofit>().create(AnimalApiService::class.java)
    }

    factory<AdotanteApiService> {
        get<Retrofit>().create(AdotanteApiService::class.java)
    }

    factory<RequisicaoApiService> {
        get<Retrofit>().create(RequisicaoApiService::class.java)
    }

    single { PerfilRepository(get()) }
    single { PerfilUseCase(get()) }

    viewModel { AnimalViewModel(get()) }
    viewModel { AnimalFavoritoViewModel(get()) }
    viewModel { PerfilViewModel(get(), get()) }
    viewModel { AdotanteViewModel(get()) }
    viewModel { RequisicaoViewModel(get()) }
    viewModel { OngViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RedefinicaoSenhaViewModel(get()) }
    viewModel { RedefinicaoOptViewModel(get()) }
    viewModel { NovaSenhaViewModel(get()) }
}