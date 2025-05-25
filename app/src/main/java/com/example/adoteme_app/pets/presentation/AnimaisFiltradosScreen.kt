package com.example.adoteme_app.pets.presentation

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.adoteme_app.model.PersonalidadeDto
import com.example.adoteme_app.pets.presentation.pets_screen.AnimalViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.adoteme_app.home.presentation.home_screen.combinarAnimaisEFavoritos
import com.example.adoteme_app.model.AnimalUiModel
import com.example.adoteme_app.pets.presentation.favoritos_screen.AnimalFavoritoViewModel
import com.example.adoteme_app.ui.components.categoriaParaPersonalidade
import com.example.adoteme_app.ui.components.loading.AnimalGrid
import org.koin.androidx.compose.koinViewModel

@Composable
fun AnimaisFiltradosScreen(
    categoriaNome: String,
    navController: NavController,
    AnimalviewModel: AnimalViewModel = koinViewModel(),
    viewModel: AnimalFavoritoViewModel = koinViewModel(),
) {
    val animais by AnimalviewModel.animais.collectAsState()
    val favoritosIds by viewModel.favoritosIds.collectAsState()

    val seletor = categoriaParaPersonalidade[categoriaNome]

    val animaisFiltrados = remember(animais, favoritosIds, categoriaNome) {
        val filtrados = seletor?.let { seletorFunc ->
            animais.sortedByDescending { seletorFunc(it.personalidade) }
        } ?: emptyList()

        combinarAnimaisEFavoritos(filtrados, favoritosIds)
    }

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    val idAdotante = sharedPreferences.getLong("userId", 0L)

    LaunchedEffect(idAdotante) {
        if (idAdotante != 0L) {
            viewModel.carregarFavoritos(idAdotante)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Animais da categoria: $categoriaNome",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        AnimalGrid(
            isLoading = animais.isEmpty(),
            animais = animaisFiltrados,
            navController = navController,
            idAdotante = idAdotante
        )
    }
}

