package com.example.adoteme_app.interfaces

import com.example.adoteme_app.auth.presentation.login_screen.auth_service.request.LoginRequest
import com.example.adoteme_app.auth.presentation.login_screen.auth_service.request.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {
    @POST("login/adotante")
    suspend fun login(@Body request: LoginRequest): Response<ResponseBody>

    @POST("login/adotante/validar-otp")
    suspend fun validarOtp(
        @Query("email") email: String,
        @Query("otp") otp: String
    ): Response<LoginResponse>
}