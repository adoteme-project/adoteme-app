package com.example.adoteme_app.api

import com.example.adoteme_app.interfaces.OngApiService
import com.example.adoteme_app.network.RetrofitInstance

object OngApi {
    val service: OngApiService = RetrofitInstance.retrofit.create(OngApiService::class.java)
}