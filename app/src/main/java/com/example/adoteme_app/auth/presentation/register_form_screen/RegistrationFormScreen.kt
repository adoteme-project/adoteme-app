package com.example.adoteme_app.auth.presentation.register_form_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.adoteme_app.model.AdotanteRequest
import com.example.adoteme_app.model.AdotanteViewModel
import com.example.adoteme_app.model.Formulario
import com.example.adoteme_app.navigation.presentation.utils.RootRoutes
import com.example.adoteme_app.perfil.presentation.utils.components.RadioButtonGroup
import com.example.adoteme_app.ui.theme.ActionColor
import com.google.gson.GsonBuilder
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationFormScreen(navController: NavController) {
    val viewModel: AdotanteViewModel = koinViewModel()

    val adotanteInfoJson = navController.previousBackStackEntry
        ?.savedStateHandle
        ?.get<String>("adotanteInfo")

    val gson = GsonBuilder().create()

    val adotanteInfo = remember(adotanteInfoJson) {
        adotanteInfoJson.let { gson.fromJson(it, AdotanteRequest::class.java) }
    }

    val cadastroConcluido by viewModel.cadastroConcluido.collectAsState()

    LaunchedEffect (cadastroConcluido) {
        if (cadastroConcluido) {
            navController.navigate(RootRoutes.Login.route) {
                popUpTo(RootRoutes.UserFormRegistration.route) { inclusive = true }
            }
        }
    }

    val (temCrianca, setTemCrianca) = remember { mutableStateOf("") }
    val (moradoresConcordam, setMoradorescConcordam) = remember { mutableStateOf("") }
    val (seraResponsavel, setSeraResponsavel) = remember { mutableStateOf("") }
    val (moraEmCasa, setMoraEmCasa) = remember { mutableStateOf("") }
    val (isTelado, setIsTelado) = remember { mutableStateOf("") }
    val (casaPortaoAlto, setCasaPortalAlto) = remember { mutableStateOf("") }
    val (temPet, setTemPet) = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar (
                title = { Text("MEUS FORMULÁRIO", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(
                        onClick = {navController.popBackStack()}
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
                        selectedOption = temCrianca,
                        onOptionSelected = setTemCrianca
                    )
                }
                item {
                    RadioButtonGroup(
                        "Todos que moram com você estão de acordo com a adoção?",
                        radioOptions = listOf("Sim", "Não"),
                        selectedOption = moradoresConcordam,
                        onOptionSelected = setMoradorescConcordam
                    )
                }
                item {
                    RadioButtonGroup(
                        "O responsável pelo animal será você?",
                        radioOptions = listOf("Eu", "Outra Pessoa"),
                        selectedOption = seraResponsavel,
                        onOptionSelected = setSeraResponsavel
                    )
                }
                item {
                    RadioButtonGroup(
                        "Você mora em casa?",
                        radioOptions = listOf("Casa", "Apartamento"),
                        selectedOption = moraEmCasa,
                        onOptionSelected = setMoraEmCasa
                    )
                }
                item {
                    RadioButtonGroup(
                        "Sua residência é telada?",
                        radioOptions = listOf("Sim", "Não"),
                        selectedOption = isTelado,
                        onOptionSelected = setIsTelado
                    )
                }
                item {
                    RadioButtonGroup(
                        "Sua casa tem portão alto?",
                        radioOptions = listOf("Sim", "Não"),
                        selectedOption = casaPortaoAlto,
                        onOptionSelected = setCasaPortalAlto
                    )
                }
                item {
                    RadioButtonGroup(
                        "Tem outros pets na sua casa?",
                        radioOptions = listOf("Sim", "Não"),
                        selectedOption = temPet,
                        onOptionSelected = setTemPet
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

                            val adotanteWithFormulario = adotanteInfo.copy(
                                formulario = Formulario(
                                    temCrianca = convertValues(temCrianca),
                                    moradoresConcordam = convertValues(moradoresConcordam),
                                    seraResponsavel = convertValues(seraResponsavel),
                                    moraEmCasa = convertValues(moraEmCasa),
                                    isTelado = convertValues(isTelado),
                                    casaPortaoAlto = convertValues(casaPortaoAlto),
                                    temPet = convertValues(temPet)
                                )
                            )

                            viewModel.cadastrarAdotante(adotanteWithFormulario, null)

                            Log.i("Form Final", adotanteWithFormulario.toString())

                        },
                    ) {
                        Text(
                            text = "Cadastrar",
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

fun convertValues(valor: String): String {
    return if (valor == "Sim" || valor == "Casa" || valor == "Eu") {
        "true"
    } else {
        "false"
    }
}