package com.example.adoteme_app.pets.presentation.pets_screen

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoteme_app.R
import com.example.adoteme_app.api.AnimalApi
import com.example.adoteme_app.model.AnimalResponse
import com.example.adoteme_app.model.Categoria
import com.example.adoteme_app.ui.components.AnimalFavoritoCard
import com.example.adoteme_app.ui.components.CategoriaCarrossel
import com.example.adoteme_app.ui.components.FilterButton
import com.example.adoteme_app.ui.components.loading.AnimalGrid
import org.koin.androidx.compose.koinViewModel

@Composable
fun PetsScreen(navController: NavController, viewModel: AnimalViewModel = koinViewModel()) {
   val animais = viewModel.animais.collectAsState()


    val listaCategiria = listOf(
        Categoria("Brincalhões", R.drawable.dog_soc),
        Categoria("Curiosos", R.drawable.dog_obd),
        Categoria("Comportados", R.drawable.cat_enr),
        Categoria("Donos do Pedaço", R.drawable.dog_soc),
        Categoria("Calmos", R.drawable.dog_soc),
        Categoria("Amigáveis", R.drawable.dog_obd)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
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

        item {
            Text("Categorias", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }

        item {
            CategoriaCarrossel(listaCategiria)
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Animais", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                FilterButton(onClick = {})
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            AnimalGrid(
                isLoading = animais.value.isEmpty(),
                animais = animais.value,
                navController = navController
            )
        }
    }
}
