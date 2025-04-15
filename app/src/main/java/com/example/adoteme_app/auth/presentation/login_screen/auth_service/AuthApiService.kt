package com.example.adoteme_app.auth.presentation.login_screen.auth_service

import com.example.adoteme_app.auth.presentation.login_screen.auth_service.Request.LoginRequest
import com.example.adoteme_app.auth.presentation.login_screen.auth_service.Request.LoginResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("/login/adotante")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}

object AuthApi{

    private const val BASE_URL = "http://10.0.2.2:8080"

    fun getApi(): AuthApiService {

        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)

        val clienteHttp = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApiService::class.java)
    }

}
