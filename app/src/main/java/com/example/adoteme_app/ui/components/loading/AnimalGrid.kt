package com.example.adoteme_app.ui.components.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adoteme_app.model.AnimalResponse
import com.example.adoteme_app.ui.components.AnimalFavoritoCard

@Composable
fun AnimalGrid(
    isLoading: Boolean,
    animais: List<AnimalResponse>,
    navController: NavController
) {
    val rows = if (isLoading) List(2) { listOf<AnimalResponse?>(null, null) }
    else animais.chunked(2).map { it.map { animal -> animal as AnimalResponse? } }
    Column {
        rows.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(21.dp)
            ) {
                rowItems.forEach { animal ->
                    Box(modifier = Modifier.weight(1f)) {
                        if (animal != null) {
                            AnimalFavoritoCard(animal, navController)
                        } else {
                            AnimalCardShimmer()
                        }
                    }
                }
                if (rowItems.size < 2) {
                    Box(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}