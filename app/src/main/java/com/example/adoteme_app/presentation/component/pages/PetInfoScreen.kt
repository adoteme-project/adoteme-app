package com.example.adoteme_app.presentation.component.pages

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.adoteme_app.domain.model.AnimalUiModel
import com.example.adoteme_app.domain.model.PersonalidadeDto
import com.example.adoteme_app.presentation.viewmodel.AnimalFavoritoViewModel
import com.example.adoteme_app.presentation.viewmodel.RequisicaoViewModel
import com.example.adoteme_app.domain.model.StatusRequisicao
import com.example.adoteme_app.presentation.viewmodel.AnimalViewModel
import com.example.adoteme_app.presentation.component.AccordionPersonality
import com.example.adoteme_app.presentation.component.AccordionSection
import com.example.adoteme_app.presentation.component.AnimalFavoritoCard
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@ExperimentalLayoutApi
fun PetInfoScreen(onBack: () -> Unit,
                  animalId: Long,
                  navController: NavController,
                  requisicaoViewModel: RequisicaoViewModel = koinViewModel(),
                  animalViewModel: AnimalViewModel = koinViewModel(),
                  animalViewModelFavoritos: AnimalFavoritoViewModel = koinViewModel()) {
    val context = LocalContext.current

    LaunchedEffect(animalId) {
        animalViewModel.carregarAnimalPorId(animalId)
    }

    val maxItemsToShow = 2

    val animalState by animalViewModel.animal.collectAsState()
    val animaisState by animalViewModel.animais.collectAsState()

    val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getLong("userId", 0L)
    val isUsuarioLogado = userId != 0L

    val statusRequisicao by requisicaoViewModel.statusRequisicao.collectAsState()

    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val minHeight = screenHeight * 0.45f

    var showModal = remember { mutableStateOf(false) }

    val actionColor = Color(red = 255, green = 166, blue = 7)
    val rejectColor = Color(red = 236, green = 90, blue = 73)
    val approvalColor = Color(red = 169, green = 185, blue = 73)

    LaunchedEffect(statusRequisicao) {
        when(statusRequisicao) {
            is StatusRequisicao.Sucesso -> {
                showBottomSheet = false
                showModal.value = true
            }
            is StatusRequisicao.RequisicaoDuplicada -> {
                showBottomSheet = false
                Toast.makeText(
                    context,
                    "Você já realizou uma solicitação de adoção para este pet.",
                    Toast.LENGTH_LONG
                ).show()
            }
            is StatusRequisicao.Erro -> {
                showBottomSheet = false
                Toast.makeText(context, "Erro durante a solicitação", Toast.LENGTH_LONG).show()
            }
            else -> {}
        }

    }

    LaunchedEffect(userId) {
        animalViewModelFavoritos.carregarFavoritos(userId)
    }

    val favoritosIds by animalViewModelFavoritos.favoritosIds.collectAsState()

    val animaisUi = remember(animaisState, favoritosIds) {
        animaisState.map { animal ->
            AnimalUiModel(
                id = animal.id,
                nome = animal.nome,
                idade = animal.idade,
                imagem = animal.imagem,
                especie = animal.especie ?: "",
                sexo = animal.sexo ?: "",
                porte = animal.porte ?: "",
                isFavoritado = favoritosIds.contains(animal.id)
            )
        }
    }

    val animaisLimitados = animaisUi.take(maxItemsToShow)


    Scaffold(
topBar = {
    TopAppBar(
        title = { Text("Detalhes - ${animalState?.nome}") },
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
                        text = animalState?.nome.toString(),
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
                    painter = rememberAsyncImagePainter(model = animalState?.imagem),
                    contentDescription = "Imagem do pet",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(vertical = 12.dp),
                    contentScale = ContentScale.Crop
                )
            }

            item {
                Text(
                    text = animalState?.nome.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 32.sp
                )
            }

//            item {
//                Text(
//                    text = "Nhoa é um pet carismático e cheio de energia! " +
//                            "Com um olhar expressivo e uma personalidade cativante, ele " +
//                            "adora brincar, correr e explorar novos ambientes.",
//                    fontSize = 14.sp
//                )
//            }

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
                            append(animalState?.especie)
                        },
                        lineHeight = 32.sp
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Sexo: ")
                            }
                            append(animalState?.sexo)
                        },
                        lineHeight = 32.sp
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Idade: ")
                            }
                            append(animalState?.idade.toString())
                        },
                        lineHeight = 32.sp
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Tamanho: ")
                            }
                            append(animalState?.porte)
                        },
                        lineHeight = 32.sp
                    )
//                    Text(
//                        text = buildAnnotatedString {
//                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
//                                append("Taxa de adoção: ")
//                            }
//                            //append(petTaxa)
//                        },
//                        lineHeight = 32.sp
//                    )
                }
            }

            item {
                AccordionPersonality(
                    sections = listOf(
                        AccordionSection(
                            title = "Personalidades",
                            rows = listOf(
                                "Energia: ${animalState?.personalidade?.energia ?: 0}",
                                "Sociabilidade: ${animalState?.personalidade?.sociabilidade ?: 0}",
                                "Tolerância: ${animalState?.personalidade?.tolerante ?: 0}",
                                "Obediência: ${animalState?.personalidade?.obediente ?: 0}",
                                "Territorialidade: ${animalState?.personalidade?.territorial ?: 0}",
                                "Inteligência: ${animalState?.personalidade?.inteligencia ?: 0}"
                            )
                        )
                    ),
                    personalidade = animalState?.personalidade ?: PersonalidadeDto(0, 0, 0, 0, 0, 0)
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
                val rows = animaisLimitados.chunked(2)
                rows.forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(21.dp)
                    ) {
                        rowItems.forEach { animal ->
                            Box(modifier = Modifier.weight(1f)) {
                                AnimalFavoritoCard(animal, navController, idAdotante = userId, isUsuarioLogado = isUsuarioLogado)
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
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
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
                                    onClick = {
                                        if (userId != 0L) {
                                            requisicaoViewModel.criarRequisicao(userId, animalId)
                                        } else {
                                            Toast.makeText(context, "Você precisa estar logado para adotar.", Toast.LENGTH_SHORT).show()
                                            showBottomSheet = false
                                        }
                                    }
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
                    ModalAdocao(onBack = onBack, setShowModal = { showModal.value = it })
                }
            }
        }
    }
}
}

@Composable
fun ModalAdocao(
    onBack: () -> Unit,
    setShowModal: (Boolean) -> Unit
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
