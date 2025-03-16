package com.example.adoteme_app.pets.presentation.favoritos_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoteme_app.model.AnimalFavorito
import com.example.adoteme_app.ui.components.AnimalFavoritoCard
import com.example.adoteme_app.R

@Composable
fun AnimalFavoritoScreen() {
    val favoriteAnimals = listOf(
        AnimalFavorito("NOAH", "2 anos", "Macho", R.drawable.animal),
        AnimalFavorito("NOAH", "2 anos", "Macho", R.drawable.animal),
        AnimalFavorito("NOAH", "2 anos", "Macho", R.drawable.animal),
        AnimalFavorito("NOAH", "2 anos", "Macho", R.drawable.animal)
    )

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
            items(favoriteAnimals.size) { index ->
                AnimalFavoritoCard(favoriteAnimals[index])
            }
        }
    }
}