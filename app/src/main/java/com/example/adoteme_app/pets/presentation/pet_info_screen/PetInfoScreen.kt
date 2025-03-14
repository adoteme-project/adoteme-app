package com.example.adoteme_app.pets.presentation.pet_info_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoteme_app.R
import com.example.adoteme_app.pets.utils.components.AccordionPersonality
import com.example.adoteme_app.pets.utils.components.AccordionSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@ExperimentalLayoutApi
fun PetInfoScreen() {
    val petEspecie: String = "Cachorro"
    val petSexo: String = "Macho"
    val petIdade: String = "2 anos"
    val petTamanho: String = "Médio"
    val petTaxa: String = "R$ 70"

    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    val actionColor = Color(
        red = 255,
        green = 166,
        blue = 7
    )

    val rejectColor = Color(
        red = 236,
        green = 90,
        blue = 73
    )
    val approvalColor = Color(
        red = 169,
        green = 185,
        blue = 73
    )


    LazyColumn(
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        item {
            Row(
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
        }

        item {
            Image(
                painter = painterResource(id = R.drawable.pet),
                contentDescription = "Pet",
                modifier = Modifier.fillMaxWidth()
                    .height(200.dp)
                    .padding(vertical = 12.dp)
            )
        }

        item {
            Text(
                text = "Noha",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                        "Proin nec mi eget ligula dignissim aliquam vitae a tellus.",
                fontSize = 14.sp
            )
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
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
        }

        item {
            AccordionPersonality(
                sections = listOf(
                    AccordionSection(
                        title = "Personalidades",
                        rows = listOf("Brincalhão", "Calmo", "Territorial")
                    )
                )
            )
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Text(text = "Sugestão", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }

        item {
            Spacer(Modifier.height(24.dp))
            Button(
                colors = ButtonColors(
                    containerColor = actionColor,
                    contentColor = Color.White,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.LightGray
                ),
                modifier = Modifier.fillMaxWidth( ),
                onClick = { showBottomSheet = true }
            ) {
                Text(
                    text = "Adotar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }

        item {
            if(showBottomSheet) {
                ModalBottomSheet(
                    modifier = Modifier.fillMaxHeight(),
                    sheetState = sheetState,
                    onDismissRequest = { showBottomSheet = false}
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(21.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Confirmação de Adoção",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Você está prestes a enviar uma solicitação de adoção. " +
                                    "Por favor, confirme que você tem certeza e está preparado" +
                                    "para fornecer um lar permanente."
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Button(
                                colors = ButtonColors(
                                    containerColor = rejectColor,
                                    contentColor = Color.White,
                                    disabledContentColor = Color.Transparent,
                                    disabledContainerColor = Color.LightGray
                                ),
                                onClick = {}
                            ) {
                                Text(
                                    "Cancelar",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.width(32.dp))
                            Button(
                                colors = ButtonColors(
                                    containerColor = approvalColor,
                                    contentColor = Color.White,
                                    disabledContentColor = Color.Transparent,
                                    disabledContainerColor = Color.LightGray
                                ),
                                onClick = {}
                            ) {
                                Text(
                                    "Aceitar",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}