package com.example.adoteme_app.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adoteme_app.domain.model.AnimalUiFavoritoDto

@Composable
fun AnimalFavoritoDtoGrid(
    isLoading: Boolean,
    animais: List<AnimalUiFavoritoDto>,
    navController: NavController,
    idAdotante: Long
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            items(4) {
                AnimalFavoritoDtoCardShimmer()
            }
        } else {
            itemsIndexed(animais) { _, animal ->
                AnimalFavoritoDtoCard(animal, navController, idAdotante = idAdotante)
            }
        }
    }
}
