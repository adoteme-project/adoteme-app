package com.example.adoteme_app.pets.presentation.pets_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoteme_app.R
import com.example.adoteme_app.model.AnimalFavorito
import com.example.adoteme_app.model.Categoria
import com.example.adoteme_app.ui.components.AnimalFavoritoCard
import com.example.adoteme_app.ui.components.CategoriaCarrossel
import com.example.adoteme_app.ui.components.FilterButton

@Composable
fun PetsScreen(navController: NavController) {

    val listaCategiria:List<Categoria> = listOf(
        Categoria("Brincalhões", R.drawable.dog_soc),
        Categoria("Curiosos", R.drawable.dog_obd),
        Categoria("Comportados", R.drawable.cat_enr),
        Categoria("Donos do Pedaço", R.drawable.dog_soc),
        Categoria("Calmos", R.drawable.dog_soc),
        Categoria("Amigáveis", R.drawable.dog_obd)
    )

    val animals = listOf(
        AnimalFavorito("NOAH", "2 anos", "Macho", R.drawable.animal),
        AnimalFavorito("NOAH", "2 anos", "Macho", R.drawable.animal),
        AnimalFavorito("NOAH", "2 anos", "Macho", R.drawable.animal),
        AnimalFavorito("NOAH", "2 anos", "Macho", R.drawable.animal)
    )

    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
    ) {
        item {
            Image(
                painter = painterResource(R.drawable.animais),
                contentDescription = "Seção de Pets",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        item { Text(text = "Categorias", fontSize = 28.sp, fontWeight = FontWeight.Bold) }
        item { CategoriaCarrossel(listaCategiria) }
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Animais", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                FilterButton(onClick = {})
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            val rows = animals.chunked(2)
            rows.forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(21.dp)
                ) {
                    rowItems.forEach { animal ->
                        Box(modifier = Modifier.weight(1f)) {
                            AnimalFavoritoCard(animal, navController)
                        }
                    }
                    if (rowItems.size < 2) {
                        Box(modifier = Modifier.weight(1f))
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }

    }

}