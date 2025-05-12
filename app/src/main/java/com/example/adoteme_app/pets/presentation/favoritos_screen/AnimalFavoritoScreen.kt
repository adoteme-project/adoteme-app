package com.example.adoteme_app.pets.presentation.favoritos_screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.example.adoteme_app.ui.components.AnimalFavoritoDtoCard
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.adoteme_app.auth.presentation.login_screen.LoginViewModel
import com.example.adoteme_app.ui.components.loading.AnimalFavoritoDtoGrid


@Composable
fun AnimalFavoritoScreen(
    navController: NavController,
    viewModel: AnimalFavoritoViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    val token = sharedPreferences.getString("token", "") ?: ""
    val userId = sharedPreferences.getLong("userId", 0L)

    val favoritos by viewModel.favoritos.collectAsState()

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
            navController = navController
        )
    }
}
