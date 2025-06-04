package com.example.adoteme_app.presentation.component.pages

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoteme_app.domain.model.FormValidator
import com.example.adoteme_app.domain.model.UserFormErros
import com.example.adoteme_app.domain.model.UserFormState
import com.example.adoteme_app.domain.model.AdotanteRequest
import com.example.adoteme_app.domain.model.Formulario
import com.example.adoteme_app.domain.model.RootRoutes
import com.example.adoteme_app.presentation.component.DatePickerFieldToModal
import com.example.adoteme_app.presentation.component.InputForm
import com.example.adoteme_app.presentation.component.PasswordInputForm
import com.example.adoteme_app.ui.theme.ActionColor
import com.google.gson.Gson
import com.google.gson.GsonBuilder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavController) {
    val formState = remember { mutableStateOf(UserFormState()) }
    val formErros = remember { mutableStateOf(UserFormErros()) }

    Scaffold (
        topBar = {
            TopAppBar (
                title = { Text("CRIAR CONTA", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(
                        onClick = {navController.popBackStack()}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Voltar"
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(horizontal = 21.dp)
        ) {
            LazyColumn (
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    HorizontalDivider()
                }
                item {
                    InputForm(
                        formState.value.nome,
                        "Nome Completo",
                        KeyboardType.Text,
                        onValueChange = { formState.value = formState.value.copy(nome = it) },
                        errorMessage = formErros.value.nome
                    )
                }
                item {
                    InputForm(
                        formState.value.email,
                    "E-mail",
                        KeyboardType.Email,
                        onValueChange = { formState.value = formState.value.copy(email = it)},
                        errorMessage = formErros.value.email
                    )
                }
                item {
                    InputForm(
                        formState.value.celular,
                        "DDD + Celular",
                        KeyboardType.Phone,
                        onValueChange = { formState.value = formState.value.copy(celular = it)},
                        errorMessage = formErros.value.celular
                    )
                }
                item {
                    DatePickerFieldToModal(
                        label = "Data de Nascimento",
                        selectedDate = formState.value.dataNascimento,
                        onDateSelected = { formState.value = formState.value.copy(dataNascimento = it)}
                    )
                }
                item {
                    InputForm(
                        formState.value.cep,
                        "CEP",
                        KeyboardType.Text,
                        onValueChange = { formState.value = formState.value.copy(cep = it) },
                        errorMessage = formErros.value.cep
                    )
                }
//                item {
//                    InputForm(
//                        formState.value.estado,
//                    "Estado",
//                        KeyboardType.Text,
//                        onValueChange = { formState.value = formState.value.copy(estado = it) }
//                    )
//                }
//                item {
//                    InputForm(
//                        formState.value.cidade,
//                        "Cidade",
//                        KeyboardType.Text,
//                        onValueChange = { formState.value = formState.value.copy(cidade = it) }
//                    )
//                }
                item {
                    InputForm(
                        formState.value.numero,
                        enabled = true,
                        label = "Numero",
                        inputType =  KeyboardType.Text,
                        onValueChange = { formState.value = formState.value.copy(numero = it) }
                    )
                }
                item {
                    PasswordInputForm(
                        formState.value.senha,
                        label = "Senha",
                        enabled = true,
                        onValueChange = { formState.value = formState.value.copy(senha = it) },
                        errorMessage = formErros.value.senha
                    )
                }
                item {BulletList()}
                item {
                    PasswordInputForm(
                        formState.value.confirmarSenha,
                        label = "Confirmar senha",
                        enabled = true,
                        onValueChange = { formState.value = formState.value.copy(confirmarSenha = it) },
                        errorMessage = formErros.value.confirmarSenha
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
                            if(validateUserForm(formState, formErros)) {
                                val adotanteInfo = AdotanteRequest(
                                    nome = formState.value.nome,
                                    cep = formState.value.cep,
                                    email = formState.value.email,
                                    senha = formState.value.senha,
                                    dtNasc = formState.value.dataNascimento,
                                    numero = formState.value.numero,
                                    celular = formState.value.celular,
                                    formulario = Formulario(
                                        temPet = "",
                                        casaPortaoAlto = "",
                                        moradoresConcordam = "",
                                        isTelado = "",
                                        seraResponsavel = "",
                                        moraEmCasa = "",
                                        temCrianca = ""
                                    )
                                )

                                val gson: Gson = GsonBuilder().create()
                                val adotanteInfoJson = gson.toJson(adotanteInfo)

                                Log.i("Forms", "Adotante Info Json: $adotanteInfoJson")

                                navController.currentBackStackEntry?.savedStateHandle?.set("adotanteInfo", adotanteInfoJson)

                                navController.navigate(RootRoutes.UserFormRegistration.route) {
                                    popUpTo(RootRoutes.UserRegistration.route) { saveState = true }
                                }
                            }
                        }
                    ) {
                        Text(
                            text = "Continuar",
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

fun validateUserForm(formState: MutableState<UserFormState>, formErros: MutableState<UserFormErros>): Boolean {
    val errors  = with(formState.value) {
        UserFormErros(
            nome = FormValidator.validateNome(nome),
            email = FormValidator.validateEmail(email),
            senha = FormValidator.validateSenha(senha),
            confirmarSenha = FormValidator.validateConfirmarSenha(senha, confirmarSenha),
            celular = FormValidator.validateCelular(celular),
            cep = FormValidator.validateCEP(cep)
        )
    }
    formErros.value = errors
    return listOf(
        errors.nome,
        errors.email,
        errors.senha,
        errors.confirmarSenha,
        errors.celular,
        errors.cep
    ).all { it == null }
}

@Composable
fun BulletList() {
    val bullet = "\u2022"
    val messages = listOf(
        "Mínimo de 8 caracteres",
        "Pelo menos uma letra maiúscula",
        "Pelo menos um número",
        "Pelo menos um símbolo especial (!@#\$%^&*)"
    )
    val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))
    Text(
        buildAnnotatedString {
            messages.forEach {
                withStyle(style = paragraphStyle) {
                    append(bullet)
                    append("\t\t")
                    append(it)
                }
            }
        }
    )
}