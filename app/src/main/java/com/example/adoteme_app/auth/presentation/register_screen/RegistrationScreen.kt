package com.example.adoteme_app.auth.presentation.register_screen

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.adoteme_app.model.AdotanteRequest
import com.example.adoteme_app.model.AdotanteViewModel
import com.example.adoteme_app.model.Formulario
import com.example.adoteme_app.navigation.presentation.utils.RootRoutes
import com.example.adoteme_app.perfil.presentation.utils.components.DatePickerFieldToModal
import com.example.adoteme_app.perfil.presentation.utils.components.InputForm
import com.example.adoteme_app.perfil.presentation.utils.components.PasswordInputForm
import com.example.adoteme_app.ui.theme.ActionColor
import com.google.gson.Gson
import com.google.gson.GsonBuilder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavController) {
    var fieldNome by remember { mutableStateOf("") }
    var fieldEmail by remember { mutableStateOf("") }
    var fieldCelular by remember { mutableStateOf("") }
    var fieldDataNascimento by remember { mutableStateOf("") }
    var fieldCep by remember { mutableStateOf("") }
    var fieldEstado by remember { mutableStateOf("") }
    var fieldCidade by remember { mutableStateOf("") }
    var fieldNumero by remember { mutableStateOf("") }
    var fieldSenha by remember { mutableStateOf("") }
    var fieldConfirmarSenha by remember { mutableStateOf("") }

    var nomeError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var senhaError by remember { mutableStateOf<String?>(null) }
    var confirmarSenhaError by remember { mutableStateOf<String?>(null) }
    var celularError by remember { mutableStateOf<String?>(null) }
    var cepError by remember { mutableStateOf<String?>(null) }

    fun validateNome(nome: String) = if (nome.isBlank() && nome.length <= 2) "Nome é obrigatório" else null
    fun validateEmail(email: String) = if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) "Email inválido" else null
    fun validateCEP(cep: String) = if (cep.length == 8) "CEP deve ter no mínimo 8 digitos" else null
    fun validateSenha(senha: String) = if (senha.length < 6) "Senha deve ter no mínimo 6 caracteres" else null
    fun validateConfirmarSenha(senha: String, confirmar: String) = if (senha != confirmar) "As senhas não coincidem" else null

    fun validateCelular(celular: String): String? {
        val regex = Regex("^\\+?[1-9]\\d{1,14}$")
        return if (!regex.matches(celular)) "Número de celular inválido (ex: +5511999999999)" else null
    }

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
                    InputForm(fieldNome, "Nome Completo", KeyboardType.Text,
                    onValueChange = { fieldNome = it }, errorMessage = nomeError) }
                item {
                    InputForm(fieldEmail, "E-mail", KeyboardType.Email,
                        onValueChange = { fieldEmail = it}, errorMessage = emailError)
                }
                item {
                    InputForm(fieldCelular, "DDD + Celular", KeyboardType.Phone,
                    onValueChange = { fieldCelular = it}, errorMessage = celularError)
                }
                item {DatePickerFieldToModal(label = "Data de Nascimento", selectedDate = fieldDataNascimento, onDateSelected = { fieldDataNascimento = it}) }
                item {
                    InputForm(fieldCep, "CEP", KeyboardType.Text,
                    onValueChange = { fieldCep = it }, errorMessage = cepError)
                }
                item {InputForm(fieldEstado, "Estado", KeyboardType.Text, onValueChange = { fieldEstado = it })}
                item {InputForm(fieldCidade, "Cidade", KeyboardType.Text, onValueChange = { fieldEstado = it })}
                item {InputForm(fieldNumero, "Numero", KeyboardType.Text, onValueChange = { fieldNumero = it })}
                item {
                    PasswordInputForm(fieldSenha, "Senha",
                    onValueChange = { fieldSenha = it }, errorMessage = senhaError )
                }
                item {BulletList()}
                item {
                    PasswordInputForm(fieldConfirmarSenha, "Confirmar senha",
                    onValueChange = { fieldConfirmarSenha = it }, errorMessage = confirmarSenhaError)
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
                            nomeError = validateNome(fieldNome)
                            emailError = validateEmail(fieldEmail)
                            senhaError = validateSenha(fieldSenha)
                            confirmarSenhaError = validateConfirmarSenha(fieldSenha, fieldConfirmarSenha)
                            celularError = validateCelular(fieldCelular)
                            cepError = validateCEP(fieldCep)

                            val hasError = listOf(nomeError, emailError, senhaError, confirmarSenhaError).any { it != null }

                            if(hasError) {
                                val adotanteInfo = AdotanteRequest(
                                    nome = fieldNome,
                                    cep = fieldCep,
                                    email = fieldEmail,
                                    senha = fieldSenha,
                                    dtNasc = fieldDataNascimento,
                                    numero = fieldNumero,
                                    celular = fieldCelular,
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