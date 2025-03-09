package com.example.adoteme_app.pets.presentation.pet_info_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoteme_app.R
import com.example.adoteme_app.pets.utils.components.AccordionPersonality
import com.example.adoteme_app.pets.utils.components.AccordionSection

@Composable
@ExperimentalLayoutApi
fun PetInfoScreen() {

    val petEspecie:String = "Cachorro"
    val petSexo:String = "Macho"
    val petIdade:String = "2 anos"
    val petTamanho:String = "Médio"
    val petTaxa:String = "R$ 70"

    Column(
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        Row (
            modifier = Modifier.padding(vertical = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Noha",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Cidade - Estado",
                fontSize = 12.sp,
            )
        }
        Image(
            painter = painterResource(id = R.drawable.pet),
            contentDescription = "Pet",
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
                .padding(vertical = 12.dp)
        )
        Text(
            text = "Noha",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                    "Proin nec mi eget ligula dignissim aliquam vitae a tellus.",
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        FlowRow(
            modifier = Modifier.padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Espécie: $petEspecie")
            Text(text = "Sexo: $petSexo")
            Text(text = "Idade: $petIdade")
            Text(text = "Tamanho: $petTamanho")
            Text(text = "Taxa de adoção: $petTaxa")
        }
        AccordionPersonality(
            sections = listOf(
                AccordionSection(
                    title = "Personalidades",
                    rows = listOf("Brincalhão", "Calmo", "Territorial")
                )
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Sugestão", fontSize = 28.sp, fontWeight = FontWeight.Bold)
    }
}