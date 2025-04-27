package com.example.adoteme_app.perfil.presentation.perfilForm_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoteme_app.perfil.presentation.utils.components.RadioButtonGroup
import com.example.adoteme_app.ui.theme.ActionColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilFormScreen(onBack: () -> Unit) {

    val temCrianca = remember { mutableStateOf("") }
    val moradoresConcordam = remember { mutableStateOf("") }
    val seraResponsavel = remember { mutableStateOf("") }
    val moraEmCasa = remember { mutableStateOf("") }
    val isTelado = remember { mutableStateOf("") }
    val casaPortaoAlto = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar (
                title = { Text("MEUS FORMULÁRIO", fontWeight = FontWeight.Bold) },
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
                    RadioButtonGroup(
                        "Moram crianças na sua casa?",
                        radioOptions = listOf("Sim", "Não"),
                        selectedOption = temCrianca.value,
                        onOptionSelected = { temCrianca.value = if (it == "Sim") "true" else "false" }
                    )
                }
                item {
                    RadioButtonGroup(
                        "Todos que moram com você estão de acordo com a adoção?",
                        radioOptions = listOf("Sim", "Não"),
                        selectedOption = moradoresConcordam.value,
                        onOptionSelected = { moradoresConcordam.value = if (it == "Sim") "true" else "false" }
                    )
                }
                item {
                    RadioButtonGroup(
                        "O responsável pelo animal será você?",
                        radioOptions = listOf("Sim", "Não"),
                        selectedOption = seraResponsavel.value,
                        onOptionSelected = { seraResponsavel.value = if (it == "Sim") "true" else "false" }
                    )
                }
                item {
                    RadioButtonGroup(
                        "Você mora em casa?",
                        radioOptions = listOf("Sim", "Não"),
                        selectedOption = moraEmCasa.value,
                        onOptionSelected = { moraEmCasa.value = if (it == "Sim") "true" else "false" }
                    )
                }
                item {
                    RadioButtonGroup(
                        "Sua residência é telada?",
                        radioOptions = listOf("Sim", "Não"),
                        selectedOption = isTelado.value,
                        onOptionSelected = { isTelado.value = if (it == "Sim") "true" else "false" }
                    )
                }
                item {
                    RadioButtonGroup(
                        "Sua casa tem portão alto?",
                        radioOptions = listOf("Sim", "Não"),
                        selectedOption = casaPortaoAlto.value,
                        onOptionSelected = { casaPortaoAlto.value = if (it == "Sim") "true" else "false" }
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
                        modifier = Modifier.fillMaxWidth( ),
                        onClick = { }
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