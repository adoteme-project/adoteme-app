package com.example.adoteme_app.presentation.component.pages

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoteme_app.domain.model.Formulario
import com.example.adoteme_app.domain.model.FormularioResponse
import com.example.adoteme_app.presentation.viewmodel.PerfilViewModel
import com.example.adoteme_app.presentation.component.RadioButtonGroup
import com.example.adoteme_app.ui.theme.ActionColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilFormScreen(
    onBack: () -> Unit,
    perfilViewModel: PerfilViewModel
) {

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getLong("userId", 0L)

    val formularioResponse by perfilViewModel.adotanteFormulario.collectAsState()
    val formAdotante = formularioResponse?.let { formatValuesFromUi(it) }

    LaunchedEffect(userId) {
        if (userId != 0L) {
            perfilViewModel.buscarFormularioUsuario(userId)
        }
    }

    val temCrianca = remember { mutableStateOf(formAdotante?.temCrianca ?: "") }
    val moradoresConcordam = remember { mutableStateOf(formAdotante?.moradoresConcordam ?: "") }
    val seraResponsavel = remember { mutableStateOf(formAdotante?.seraResponsavel ?: "") }
    val moraEmCasa = remember { mutableStateOf(formAdotante?.moraEmCasa ?: "") }
    val isTelado = remember { mutableStateOf(formAdotante?.isTelado ?: "") }
    val casaPortaoAlto = remember { mutableStateOf(formAdotante?.casaPortaoAlto ?: "") }
    val temPet = remember { mutableStateOf(formAdotante?.temPet ?: "") }

    LaunchedEffect(formAdotante) {
        formAdotante?.let {
            temCrianca.value = it.temCrianca
            moradoresConcordam.value = it.moradoresConcordam
            seraResponsavel.value = it.seraResponsavel
            moraEmCasa.value = it.moraEmCasa
            isTelado.value = it.isTelado
            casaPortaoAlto.value = it.casaPortaoAlto
            temPet.value = it.temPet
        }
    }

    val isEditing = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar (
                title = { Text("MEU FORMULÁRIO", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Voltar",
                            tint = Color(
                                red = 198,
                                green = 214,
                                blue = 104
                            )
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(horizontal = 21.dp)) {
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(21.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                    RadioButtonGroup(
                        "Moram crianças na sua casa?",
                        radioOptions = listOf("Sim", "Não"),
                        selectedOption = formAdotante?.temCrianca ?: "",
                        onOptionSelected = { temCrianca.value = it },
                        enabled = isEditing.value
                    )
                }
                item {
                    RadioButtonGroup(
                        "Todos que moram com você estão de acordo com a adoção?",
                        radioOptions = listOf("Sim", "Não"),
                        selectedOption = moradoresConcordam.value,
                        onOptionSelected = { moradoresConcordam.value = it },
                        enabled = isEditing.value
                    )
                }
                item {
                    RadioButtonGroup(
                        "O responsável pelo animal será você?",
                        radioOptions = listOf("Eu", "Outra Pessoa"),
                        selectedOption = seraResponsavel.value,
                        onOptionSelected = { seraResponsavel.value = it },
                        enabled = isEditing.value
                    )
                }
                item {
                    RadioButtonGroup(
                        "Você mora em casa?",
                        radioOptions = listOf("Casa", "Apartamento"),
                        selectedOption = moraEmCasa.value,
                        onOptionSelected = { moraEmCasa.value = it },
                        enabled = isEditing.value
                    )
                }
                item {
                    RadioButtonGroup(
                        "Sua residência é telada?",
                        radioOptions = listOf("Sim", "Não"),
                        selectedOption = isTelado.value,
                        onOptionSelected = { isTelado.value = it },
                        enabled = isEditing.value
                    )
                }
                item {
                    RadioButtonGroup(
                        "Sua casa tem portão alto?",
                        radioOptions = listOf("Sim", "Não"),
                        selectedOption = casaPortaoAlto.value,
                        onOptionSelected = { casaPortaoAlto.value = it },
                        enabled = isEditing.value
                    )
                }
                item {
                    RadioButtonGroup(
                        "Tem outros pets na sua casa?",
                        radioOptions = listOf("Sim", "Não"),
                        selectedOption = temPet.value,
                        onOptionSelected = { temPet.value = it},
                        enabled = isEditing.value
                    )
                }
                item {
                    Button(
                        colors = ButtonColors(
                            containerColor = ActionColor,
                            contentColor = Color.White,
                            disabledContentColor = Color.Transparent,
                            disabledContainerColor = Color.LightGray
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (isEditing.value) {
                                val novoFormulario = convertToFormularioResponse(
                                    temCrianca.value,
                                    moradoresConcordam.value,
                                    seraResponsavel.value,
                                    moraEmCasa.value,
                                    isTelado.value,
                                    casaPortaoAlto.value,
                                    temPet.value
                                )
                                perfilViewModel.atualizarAdotanteFormulario(userId, novoFormulario)
                                isEditing.value = false
                            } else {
                                isEditing.value = true
                            }
                        }
                    ) {
                        Text(
                            text = "Alterar Formulário",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

fun formatValuesFromUi(formulario: FormularioResponse): Formulario {
    return Formulario(
        temCrianca = if (formulario.temCrianca) "Sim" else "Não",
        moradoresConcordam = if (formulario.moradoresConcordam) "Sim" else "Não",
        temPet = if (formulario.temPet) "Sim" else "Não",
        seraResponsavel = if (formulario.seraResponsavel) "Eu" else "Outra Pessoa",
        moraEmCasa = if (formulario.moraEmCasa) "Casa" else "Apartamento",
        isTelado = if (formulario.isTelado) "Sim" else "Não",
        casaPortaoAlto = if (formulario.casaPortaoAlto) "Sim" else "Não"
    )
}

fun convertToFormularioResponse(
    temCrianca: String,
    moradoresConcordam: String,
    seraResponsavel: String,
    moraEmCasa: String,
    isTelado: String,
    casaPortaoAlto: String,
    temPet: String,
): FormularioResponse {
    return FormularioResponse(
        temCrianca = temCrianca == "Sim",
        moradoresConcordam = moradoresConcordam == "Sim",
        temPet = temPet == "Sim",
        seraResponsavel = seraResponsavel == "Eu",
        moraEmCasa = moraEmCasa == "Casa",
        isTelado = isTelado == "Sim",
        casaPortaoAlto = casaPortaoAlto == "Sim"
    )
}