package com.example.adoteme_app.presentation.component.pages

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.adoteme_app.domain.model.AnimalUiModel
import com.example.adoteme_app.presentation.viewmodel.AnimalFavoritoViewModel
import com.example.adoteme_app.presentation.viewmodel.AnimalViewModel
import com.example.adoteme_app.presentation.component.TelaComFiltro
import com.example.adoteme_app.presentation.component.AnimalGrid
import org.koin.androidx.compose.koinViewModel

@Composable
fun PetsScreen(
    navController: NavController,
    viewModel: AnimalViewModel = koinViewModel(),
    animalFavoritoViewModel: AnimalFavoritoViewModel = koinViewModel()
) {
    val animaisResponse = viewModel.animais.collectAsState()
    val favoritosIds by animalFavoritoViewModel.favoritosIds.collectAsState()

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getLong("userId", 0L)

    LaunchedEffect(userId) {
        animalFavoritoViewModel.carregarFavoritos(userId)
    }


    val animaisUi = remember(animaisResponse.value, favoritosIds) {
        animaisResponse.value.map { animal ->
            AnimalUiModel(
                id = animal.id,
                nome = animal.nome,
                idade = animal.idade,
                imagem = animal.imagem,
                especie = animal.especie ?: "",
                sexo = animal.sexo ?: "",
                porte = animal.porte ?: "",
                isFavoritado = favoritosIds.contains(animal.id)
            )
        }
    }

    TelaComFiltro(
        titulo = "Animais",
        listaOriginal = animaisUi,
        navController = navController,
        aplicarFiltro = { animal, filtrosSelecionados ->
            (filtrosSelecionados.tamanho.isBlank() || filtrosSelecionados.tamanho == "Todos os Tamanhos" || animal.porte.equals(filtrosSelecionados.tamanho, ignoreCase = true)) &&
                    (filtrosSelecionados.especie.isBlank() || filtrosSelecionados.especie == "Todas as espécies" || animal.especie.equals(filtrosSelecionados.especie, ignoreCase = true)) &&
                    (filtrosSelecionados.sexo.isBlank() || filtrosSelecionados.sexo == "Todos os sexos" || animal.sexo.equals(filtrosSelecionados.sexo, ignoreCase = true))
        },
        exibirGrid = { animaisFiltrados ->
            AnimalGrid(
                isLoading = animaisUi.isEmpty(),
                animais = animaisFiltrados,
                navController = navController,
                idAdotante = userId
            )
        }
    )
}



