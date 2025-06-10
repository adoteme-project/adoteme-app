package com.example.adoteme_app.presentation.component.pages

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
import com.example.adoteme_app.presentation.viewmodel.AnimalViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoteme_app.presentation.viewmodel.AnimalFavoritoViewModel
import com.example.adoteme_app.presentation.component.categoriaParaPersonalidade
import com.example.adoteme_app.presentation.component.AnimalGrid
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

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {
            Text(
                text = "Animais da categoria: ",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text =  "$categoriaNome",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        item {
            AnimalGrid(
                isLoading = animais.isEmpty(),
                animais = animaisFiltrados,
                navController = navController,
                idAdotante = idAdotante
            )
        }

    }
}

