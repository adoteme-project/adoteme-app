package com.example.adoteme_app.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.adoteme_app.domain.model.AnimalUiFavoritoDto
import com.example.adoteme_app.domain.model.InternalRoutes
import com.example.adoteme_app.presentation.viewmodel.AnimalFavoritoViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

@Composable
fun AnimalFavoritoDtoCard(animal: AnimalUiFavoritoDto, navController: NavController, idAdotante: Long, viewModel: AnimalFavoritoViewModel = koinViewModel()) {
    val favoritos by viewModel.favoritosIds.collectAsState()

    val isFavorito = favoritos.contains(animal.animalId)

    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(3f / 4f),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDF6F0))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = animal.imagem,
                contentDescription = "Imagem de ${animal.nome}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .height(100.dp)
                    .fillMaxWidth()
            )

            IconButton(
                onClick = {
                    viewModel.favoritarOuDesfavoritar(idAdotante, animal.animalId)
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp)
                    .size(28.dp)
                    .background(Color.White.copy(alpha = 0.7f), shape = CircleShape)
            ) {
                Icon(
                    imageVector = if (isFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = if (isFavorito) "Desfavoritar" else "Favoritar",
                    tint = Color.Red
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(animal.nome.ifBlank { "Nome não informado" }, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("${animal.idade} anos, ${animal.sexo}", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        navController.navigate("petInfo/${animal.animalId}") {
                            popUpTo(InternalRoutes.Home.route) { saveState = true }
                            launchSingleTop = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726)),
                    modifier = Modifier
                        .width(100.dp)
                        .height(32.dp)
                        .clip(RoundedCornerShape(50)),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text("Ver mais", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.White)
                }
            }
        }
    }
}
