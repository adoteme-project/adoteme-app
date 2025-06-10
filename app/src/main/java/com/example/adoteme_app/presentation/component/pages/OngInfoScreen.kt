package com.example.adoteme_app.presentation.component.pages

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoteme_app.domain.model.AnimalUiModel
import com.example.adoteme_app.presentation.viewmodel.AnimalFavoritoViewModel
import com.example.adoteme_app.presentation.viewmodel.OngViewModel
import com.example.adoteme_app.presentation.component.DoacaoDialog
import com.example.adoteme_app.presentation.component.AnimalGrid
import org.koin.androidx.compose.koinViewModel

@Composable
fun OngInfoScreen(ongId: Long, navController: NavController, ongViewModel: OngViewModel = koinViewModel(), favoritoViewModel: AnimalFavoritoViewModel = koinViewModel()) {
    val ongSelecionada by ongViewModel.ongSelecionada.collectAsState()
    val favoritosIds by favoritoViewModel.favoritosIds.collectAsState()

    var showDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getLong("userId", 0L)

    LaunchedEffect(ongId) {
        ongViewModel.carregarOngPorId(ongId)
    }

    LaunchedEffect(userId) {
        favoritoViewModel.carregarFavoritos(userId)
    }

    val animais = ongSelecionada?.animais ?: emptyList()

    val animaisComFavorito = remember(animais, favoritosIds) {
        animais.map { animal ->
            AnimalUiModel(
                id = animal.id,
                nome = animal.nome,
                idade = animal.idade,
                imagem = animal.imagem,
                isFavoritado = favoritosIds.contains(animal.id)
            )
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = ongSelecionada?.nome ?: "", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Button(onClick = { showDialog = true }) {
                    Text("Doar")
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            AnimalGrid(
                isLoading = animais.isEmpty(),
                animais = animaisComFavorito,
                navController = navController,
                idAdotante = userId
            )
        }
    }

    if (showDialog && ongSelecionada != null) {
        DoacaoDialog(
            onDismiss = { showDialog = false },
            dadosBancariosDto = ongSelecionada!!
        )
    }
}

