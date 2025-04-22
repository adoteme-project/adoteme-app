package com.example.adoteme_app.pets.presentation.ongs_screen

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun OngsScreen(navController: NavController, viewModel: OngsViewModel = viewModel()) {
    val ongs = viewModel.ongs.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.listarOngs()
    }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
            Image(
                painter = painterResource(R.drawable.ongs),
                contentDescription = "Seção de Ongs",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        item { SearchBar() }
        items(ongs.value) { ong ->
            Spacer(modifier = Modifier.height(16.dp))
            OngCard(
                nome = ong.nome,
                endereco = ong.endereco.bairro,
                descricao = ong.descricao,
                distancia = ong.distancia,
                logo = ong.imagem,
                categoriaColor = Color(0xFF1E88E5)
            )
        }
    }
}