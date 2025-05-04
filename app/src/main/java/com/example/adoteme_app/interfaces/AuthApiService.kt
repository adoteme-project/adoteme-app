package com.example.adoteme_app.interfaces

import com.example.adoteme_app.auth.presentation.login_screen.auth_service.request.LoginRequest
import com.example.adoteme_app.auth.presentation.login_screen.auth_service.request.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("login/adotante")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}