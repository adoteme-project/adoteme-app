package com.example.adoteme_app.api

import com.example.adoteme_app.interfaces.AnimalApiService
import com.example.adoteme_app.network.RetrofitInstance

object AnimalApi {
    val service: AnimalApiService = RetrofitInstance.retrofit.create(AnimalApiService::class.java)
}
