package com.example.adoteme_app.pets.utils.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RatingBarPersonality(
    maxRating:Int = 5,
    currentRating:Int,
    // onRatingSelected: (Int) -> Unit
) {
    Row {
        for (i in 1..maxRating) {
            Icon(
                imageVector = Icons.Default.Pets,
                contentDescription = "Contador de Personalidade",
                tint = if (i <= currentRating) Color.Yellow else Color.Gray,
                // modifier = Modifier.clickable { onRatingSelected(i) }
            )
        }
    }
}