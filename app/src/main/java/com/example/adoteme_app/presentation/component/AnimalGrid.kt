package com.example.adoteme_app.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adoteme_app.domain.model.AnimalUiModel

@Composable
fun AnimalGrid(
    isLoading: Boolean,
    animais: List<AnimalUiModel>,
    navController: NavController,
    idAdotante: Long
) {
    val isUsuarioLogado = idAdotante != 0L
    val rows = if (isLoading) List(2) { listOf<AnimalUiModel?>(null, null) }
    else animais.chunked(2).map { it.map { animal -> animal as AnimalUiModel? } }

    Column {
        rows.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(21.dp)
            ) {
                rowItems.forEach { animal ->
                    Box(modifier = Modifier.weight(1f)) {
                        if (animal != null) {
                            AnimalFavoritoCard(
                                animal = animal,
                                navController = navController,
                                idAdotante = idAdotante,
                                isUsuarioLogado = isUsuarioLogado
                            )
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
