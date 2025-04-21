package com.example.adoteme_app.pets.presentation.favoritos_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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


@Composable
fun AnimalFavoritoScreen(navController: NavController, adotanteId: Long, viewModel: AnimalFavoritoViewModel = koinViewModel()) {
   val favoritos by viewModel.favoritos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.carregarFavoritos(adotanteId)
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

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(favoritos) { animal ->
                AnimalFavoritoDtoCard(animal, navController)
            }
        }
    }
}