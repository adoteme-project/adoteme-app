package com.example.adoteme_app.home.presentation.home_screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.adoteme_app.R
import com.example.adoteme_app.model.AnimalResponse
import com.example.adoteme_app.model.AnimalUiModel
import com.example.adoteme_app.model.Categoria
import com.example.adoteme_app.navigation.presentation.utils.InternalRoutes
import com.example.adoteme_app.pets.presentation.favoritos_screen.AnimalFavoritoViewModel
import com.example.adoteme_app.pets.presentation.pets_screen.AnimalViewModel
import com.example.adoteme_app.ui.components.AnimalFavoritoCard
import com.example.adoteme_app.ui.components.BannerCarrossel
import com.example.adoteme_app.ui.components.CategoriaCarrossel
import com.example.adoteme_app.ui.components.loading.AnimalGrid
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController, nestedNavController: NavController, viewModel: AnimalViewModel = koinViewModel(), viewModelFavoritos: AnimalFavoritoViewModel = koinViewModel()) {
    val bannerRoutes = mapOf(
        R.drawable.animais to InternalRoutes.Pets,
        R.drawable.doacoes to InternalRoutes.Ongs,
        R.drawable.ongs to InternalRoutes.Ongs
    )

    val context = LocalContext.current
    val animais = viewModel.animais.collectAsState()

    val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getLong("userId", 0L)

    val favoritosIds = viewModelFavoritos.favoritosIds.collectAsState()

    val animaisComFavorito = remember(animais.value, favoritosIds.value) {
        combinarAnimaisEFavoritos(animais.value, favoritosIds.value)
    }

    val listaCategoria: List<Categoria> = listOf(
        Categoria("Brincalhões", R.drawable.dog_soc),
        Categoria("Curiosos", R.drawable.dog_obd),
        Categoria("Comportados", R.drawable.cat_enr),
        Categoria("Donos do Pedaço", R.drawable.dog_territorial),
        Categoria("Calmos", R.drawable.cat_tol),
        Categoria("Amigáveis", R.drawable.dog_int)
    )

    LaunchedEffect(Unit) {
        viewModel.carregarAnimais()
        viewModelFavoritos.carregarFavoritos(userId)
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
    ) {
        item {
            BannerCarrossel(bannerRoutes, nestedNavController)
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            Text(text = "Categorias", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }
        item {
            CategoriaCarrossel(listaCategoria)
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Próximos a você", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }
        item {
            AnimalGrid(
                isLoading = animais.value.isEmpty(),
                animais = animaisComFavorito,
                navController = navController,
                idAdotante = userId
            )
        }

    }
}


fun combinarAnimaisEFavoritos(
    animais: List<AnimalResponse>,
    favoritosIds: Set<Long>
): List<AnimalUiModel> {
    return animais.map { animal ->
        AnimalUiModel(
            id = animal.id,
            nome = animal.nome,
            idade = animal.idade,
            imagem = animal.imagem,
            especie = animal.especie,
            sexo = animal.sexo,
            porte = animal.porte,
            isFavoritado = favoritosIds.contains(animal.id)
        )
    }
}
