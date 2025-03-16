package com.example.adoteme_app.home.presentation.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoteme_app.R
import com.example.adoteme_app.model.Categoria
import com.example.adoteme_app.ui.components.BannerCarrossel
import com.example.adoteme_app.ui.components.CategoriaCarrossel
import com.example.adoteme_app.ui.components.GridLayout.Animal
import com.example.adoteme_app.ui.components.GridLayout.GridLayout

@Composable
fun HomeScreen() {
    val banners = listOf(R.drawable.animais, R.drawable.achados, R.drawable.ongs, R.drawable.doacoes)

    val listaCategiria:List<Categoria> = listOf(
        Categoria("Brincalhões", R.drawable.dog_soc),
        Categoria("Curiosos", R.drawable.dog_obd),
        Categoria("Comportados", R.drawable.cat_enr),
        Categoria("Donos do Pedaço", R.drawable.dog_soc),
        Categoria("Calmos", R.drawable.dog_soc),
        Categoria("Amigáveis", R.drawable.dog_obd)
    )

    val animals = listOf(
        Animal(
            id = 1,
            name = "Rex",
            idade = 3,
            sexo = "Macho",
            imageUrl =""
        ),
        Animal(
            id = 2,
            name = "Luna",
            idade = 2,
            sexo = "Fêmea",
            imageUrl = "https://adimax.com.br/wp-content/uploads/2022/05/cuidados-filhote-de-cachorro.jpg")
        ,)

    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
    ) {
        BannerCarrossel(banners)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Categorias", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        CategoriaCarrossel(listaCategiria)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Próximos a você", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        GridLayout(animals = animals, columns = 2)
    }
}