package com.example.adoteme_app.pets.presentation.pets_screen

import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.getValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.adoteme_app.R
import com.example.adoteme_app.api.AnimalApi
import com.example.adoteme_app.model.AnimalResponse
import com.example.adoteme_app.model.AnimalUiModel
import com.example.adoteme_app.model.Categoria
import com.example.adoteme_app.model.FiltrosSelecionados
import com.example.adoteme_app.pets.presentation.favoritos_screen.AnimalFavoritoViewModel
import com.example.adoteme_app.ui.components.AnimalFavoritoCard
import com.example.adoteme_app.ui.components.CategoriaCarrossel
import com.example.adoteme_app.ui.components.FilterButton
import com.example.adoteme_app.ui.components.FiltroSidebar
import com.example.adoteme_app.ui.components.TelaComFiltro
import com.example.adoteme_app.ui.components.loading.AnimalGrid
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



