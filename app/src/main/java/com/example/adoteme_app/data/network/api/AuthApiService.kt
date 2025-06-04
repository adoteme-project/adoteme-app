package com.example.adoteme_app.data.network.api

import com.example.adoteme_app.domain.model.LoginRequest
import com.example.adoteme_app.domain.model.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthApiService {
    @POST("login/adotante")
    suspend fun login(@Body request: LoginRequest): Response<ResponseBody>

    @POST("login/adotante/validar-otp")
    suspend fun validarOtp(
        @Query("email") email: String,
        @Query("otp") otp: String
    ): Response<LoginResponse>

    @POST("login/ativar/{email}")
    suspend fun ativarTwoFactorAuth(@Path("email") email: String): Response<String>
}