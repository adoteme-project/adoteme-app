package com.example.adoteme_app.pets.presentation.pet_info_screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.adoteme_app.R
import com.example.adoteme_app.model.AnimalFavorito
import com.example.adoteme_app.model.RequisicaoCreateDto
import com.example.adoteme_app.pets.utils.components.AccordionPersonality
import com.example.adoteme_app.pets.utils.components.AccordionSection
import com.example.adoteme_app.ui.components.AnimalFavoritoCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@ExperimentalLayoutApi
fun PetInfoScreen(onBack: () -> Unit, navController: NavController) {
    val adocaoViewModel: RequisicaoViewModel = viewModel()
    val status by adocaoViewModel.estadoAdocao.collectAsState()

    val petEspecie: String = "Cachorro"
    val petSexo: String = "Macho"
    val petIdade: String = "2 anos"
    val petTamanho: String = "Médio"
    val petTaxa: String = "R$ 70"
    val petNome: String = "Nhoa"

    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val minHeight = screenHeight * 0.45f

    val showModal = remember { mutableStateOf(false) }

    val actionColor = Color(red = 255, green = 166, blue = 7)
    val rejectColor = Color(red = 236, green = 90, blue = 73)
    val approvalColor = Color(red = 169, green = 185, blue = 73)
    val animals = listOf(
        AnimalFavorito("NOAH", "2 anos", "Macho", R.drawable.animal),
        AnimalFavorito("NOAH", "2 anos", "Macho", R.drawable.animal),
    )


Scaffold(
topBar = {
    TopAppBar(
        title = { Text("Detalhes - $petNome") },
        navigationIcon = {
            IconButton(
                onClick = onBack
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = "Voltar",
                )
            }
        },
    )
}
) {
    innerPadding ->
    Box(modifier = Modifier.padding(innerPadding)) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = petNome,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Guarulhos - São Paulo",
                        fontSize = 12.sp,
                    )
                }
            }

            item {
                Image(
                    painter = painterResource(id = R.drawable.pet),
                    contentDescription = "Pet",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(vertical = 12.dp)
                )
            }

            item {
                Text(
                    text = "Noha",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 32.sp
                )
            }

            item {
                Text(
                    text = "Nhoa é um pet carismático e cheio de energia! " +
                            "Com um olhar expressivo e uma personalidade cativante, ele " +
                            "adora brincar, correr e explorar novos ambientes.",
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
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Espécie: ")
                            }
                            append(petEspecie)
                        },
                        lineHeight = 32.sp
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Sexo: ")
                            }
                            append(petSexo)
                        },
                        lineHeight = 32.sp
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Idade: ")
                            }
                            append(petIdade)
                        },
                        lineHeight = 32.sp
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Tamanho: ")
                            }
                            append(petTamanho)
                        },
                        lineHeight = 32.sp
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Taxa de adoção: ")
                            }
                            append(petTaxa)
                        },
                        lineHeight = 32.sp
                    )
                }
            }

            item {
                AccordionPersonality(
                    sections = listOf(
                        AccordionSection(
                            title = "Personalidades",
                            rows = listOf(
                                "Curiosos", "Amigáveis", "Tolerante",
                                "Obediente", "Territorialista", "Inteligente"
                            )
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
                Spacer(modifier = Modifier.height(12.dp))
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

            item {
                Spacer(Modifier.height(24.dp))
                Button(
                    colors = ButtonColors(
                        containerColor = actionColor,
                        contentColor = Color.White,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.LightGray
                    ),
                    modifier = Modifier.fillMaxWidth(),
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
                if (showBottomSheet) {
                    ModalBottomSheet(
                        modifier = Modifier.heightIn(min = minHeight),
                        sheetState = sheetState,
                        onDismissRequest = { showBottomSheet = false },
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(21.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Co  nfirmação de Adoção",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Você está prestes a enviar uma solicitação de adoção. " +
                                        "Por favor, confirme que você tem certeza e está preparado" +
                                        "para fornecer um lar permanente."
                            )
                            Spacer(modifier = Modifier.height(21.dp))
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
                                    onClick = { showBottomSheet = false }
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
                                    onClick = {  adocaoViewModel.adotarAnimal(idUsuario, idAnimal),
                                        showModal.value = true }
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

            item {
                if (showModal.value) {
                    ModalAdocao(onBack, setShowModal = { showModal.value = it })
                }
            }
        }
    }
}
}

@Composable
fun ModalAdocao(
    onBack: () -> Unit,
    setShowModal: (Boolean) -> Unit = {}
) {
    Dialog(onDismissRequest = {
        onBack()
        setShowModal(false)
    }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "Seu interesse em adotar foi registrado! Verifique " +
                        "a aba “Meus Pets” , na sessão do seu perfil, para atualizações.",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}
