package com.example.adoteme_app.ui.components.GridLayout

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.sql.Blob
import java.util.UUID

data class Animal(
    val id: Int,
    val name: String,
    val idade: Int,
    val sexo: String,
    val imageUrl: String
)