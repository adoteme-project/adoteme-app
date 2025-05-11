package com.example.adoteme_app.pets.presentation.ong_info_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoteme_app.model.AnimalOngDto
import com.example.adoteme_app.model.AnimalResponse
import com.example.adoteme_app.model.OngDadosBancariosAnimalDto
import com.example.adoteme_app.model.PersonalidadeDto
import com.example.adoteme_app.pets.presentation.ongs_screen.OngViewModel
import com.example.adoteme_app.ui.components.AnimalFavoritoCard
import com.example.adoteme_app.ui.components.DoacaoDialog
import com.example.adoteme_app.ui.components.FilterButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun OngInfoScreen(onBack: () -> Unit, ongId: Long, navController: NavController, ongViewModel: OngViewModel = koinViewModel()) {
    val ongSelecionada by ongViewModel.ongSelecionada.collectAsState()

    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(ongId) {
        ongViewModel.carregarOngPorId(ongId)
    }

    val animais = ongSelecionada?.animais ?: emptyList()
    val chunkedAnimais = animais.chunked(2)

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
                FilterButton(onClick = {})
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        items(chunkedAnimais.size) { index ->
            val rowItems = chunkedAnimais[index]
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(21.dp)
            ) {
                rowItems.forEach { animal ->
                    Box(modifier = Modifier.weight(1f)) {
                        AnimalFavoritoCard(
                            animal = animal.toAnimalResponse(),
                            navController = navController
                        )
                    }
                }
                if (rowItems.size < 2) {
                    Box(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }

    Button(onClick = { showDialog = true }) {
        Text("Doar")
    }

    if (showDialog && ongSelecionada != null) {
        DoacaoDialog(
            onDismiss = { showDialog = false },
            dadosBancariosDto = ongSelecionada!!
        )
    }
}

fun AnimalOngDto.toAnimalResponse(): AnimalResponse {
    return AnimalResponse(
        id = this.id,
        nome = this.nome,
        idade = this.idade,
        imagem = this.imagem,
        imagem2 = null,
        imagem3 = null,
        imagem4 = null,
        imagem5 = null,
        distancia = null,
        porte = "",
        especie = "",
        sexo = "",
        personalidade = PersonalidadeDto(
            energia = 0,
            sociabilidade = 0,
            tolerante = 0,
            obediente = 0,
            territorial = 0,
            inteligencia = 0
        )
    )
}

