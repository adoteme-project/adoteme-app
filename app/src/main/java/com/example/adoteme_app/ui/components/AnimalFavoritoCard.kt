package com.example.adoteme_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.adoteme_app.model.AdotanteViewModel
import com.example.adoteme_app.model.AnimalResponse
import com.example.adoteme_app.model.AnimalUiModel
import com.example.adoteme_app.navigation.presentation.utils.InternalRoutes
import com.example.adoteme_app.pets.presentation.favoritos_screen.AnimalFavoritoViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun AnimalFavoritoCard(animal: AnimalUiModel, navController: NavController, idAdotante: Long,  viewModel: AnimalFavoritoViewModel = koinViewModel()) {
    val favoritos by viewModel.favoritosIds.collectAsState()

    val isFavorito = favoritos.contains(animal.id)


    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDF6F0))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.height(170.dp)) {
                AsyncImage(
                    model = animal.imagem,
                    contentDescription = "Imagem de ${animal.nome}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                )

                IconButton(
                    onClick = {
                        viewModel.favoritarOuDesfavoritar(idAdotante, animal.id)
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(28.dp)
                        .background(Color.White.copy(alpha = 0.7f), shape = CircleShape)
                ) {
                    Icon(
                        imageVector = if (isFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavorito) "Desfavoritar" else "Favoritar",
                        tint = Color.Red
                    )
                }
            }

            Card(
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF0DC)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(animal.nome, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("${animal.idade} anos, ${animal.sexo}", fontSize = 13.sp, color = Color.Gray)

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = {
                            navController.navigate(InternalRoutes.PetsInfo.withId(animal.id))
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
}

