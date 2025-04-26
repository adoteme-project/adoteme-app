package com.example.adoteme_app.auth.presentation.register_screen

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

fun String.toRequestBodyFormData(): RequestBody{
    return this.toRequestBody("text/plain".toMediaTypeOrNull())
}
