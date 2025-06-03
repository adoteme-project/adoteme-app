package com.example.adoteme_app.presentation.component.pages

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.adoteme_app.presentation.viewmodel.AnimalFavoritoViewModel
import com.example.adoteme_app.presentation.component.AnimalFavoritoDtoGrid


@Composable
fun AnimalFavoritoScreen(
    navController: NavController,
    viewModel: AnimalFavoritoViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    val token = sharedPreferences.getString("token", "") ?: ""
    val userId = sharedPreferences.getLong("userId", 0L)

    val favoritos by viewModel.favoritosCompletos.collectAsState()

    LaunchedEffect(token) {
        viewModel.carregarFavoritos(userId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Favoritos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        AnimalFavoritoDtoGrid(
            isLoading = favoritos.isEmpty(),
            animais = favoritos,
            navController = navController,
            idAdotante = userId
        )
    }
}

