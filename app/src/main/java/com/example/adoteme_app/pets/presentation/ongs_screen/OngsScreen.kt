package com.example.adoteme_app.pets.presentation.ongs_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.adoteme_app.R
import com.example.adoteme_app.model.Ong
import com.example.adoteme_app.ui.components.OngCard
import com.example.adoteme_app.ui.components.SearchBar
import androidx.compose.foundation.lazy.items

@Composable
fun OngsScreen() {
    val ongs = listOf(
        Ong("CÃO SEM DONO", "São Paulo - SP", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "A 5 km de distância", R.drawable.ong1, Color(0xFF1E88E5)),
        Ong("VICA", "São Paulo - SP", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "A 5 km de distância", R.drawable.ong2, Color(0xFF8BC34A))
    )

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item { SearchBar() }
        items(ongs) { ong ->
            Spacer(modifier = Modifier.height(16.dp))
            OngCard(
                nome = ong.nome,
                endereco = ong.endereco,
                descricao = ong.descricao,
                distancia = ong.distancia,
                logo = ong.logo,
                categoriaColor = ong.categoriaColor
            )
        }
    }
}