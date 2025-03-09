package com.example.adoteme_app.ui.components.GridLayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun AnimalCard(
    animal: Animal,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDF6F0)),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(200.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            // Exibe a imagem do animal
            AnimalImage(animal.imageUrl)
            Spacer(modifier = Modifier.height(10.dp))

            // Informações do animal e botão "Ver Mais"
            AnimalInfoAndButton(animal)
        }
    }
}

@Composable
private fun AnimalImage(imageUrl: String) {
    if (imageUrl.isNotBlank()) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = "Imagem animal",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentScale = ContentScale.Crop
        )
    } else {
        Text(
            text = "Erro ao carregar imagem do animal",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.height(120.dp).padding(top = 50.dp),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun AnimalInfoAndButton(animal: Animal) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(Color(0xFFFFDFC4))
    ) {
        // Informações do animal (nome, idade, sexo)
        AnimalInfo(animal)

        // Botão "Ver Mais"
        VerMaisButton()
    }
}

@Composable
private fun AnimalInfo(animal: Animal) {
    Column(
        modifier = Modifier
            .clip(CircleShape)
            .padding(10.dp)
    ) {
        Text(
            text = animal.name,
            style = TextStyle(fontSize = 14.sp)
        )
        Text(
            text = "${animal.idade} anos, ${animal.sexo}",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF444444)
            )
        )
    }
}

@Composable
private fun VerMaisButton() {
    Column(
        modifier = Modifier
            .padding(8.dp))
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFA607))
                .clickable { VerMais() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Ver Mais",
                style = TextStyle(
                    fontSize = 10.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

private fun VerMais() {
    println("Olá")
}