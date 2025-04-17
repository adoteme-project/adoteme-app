package com.example.adoteme_app.auth.presentation.login_screen.auth_service

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(val token: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.i("api", "token para header: ${token}")

        val request = chain.request().newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(request);
    }

}