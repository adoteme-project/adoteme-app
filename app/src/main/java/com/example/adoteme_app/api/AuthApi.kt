package com.example.adoteme_app.api

import com.example.adoteme_app.interfaces.AuthApiService
import com.example.adoteme_app.network.RetrofitInstance

object AuthApi {
    val service: AuthApiService = RetrofitInstance.retrofit.create(AuthApiService::class.java)
}
