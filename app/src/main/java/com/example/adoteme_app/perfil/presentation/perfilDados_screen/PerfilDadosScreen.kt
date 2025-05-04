package com.example.adoteme_app.perfil.presentation.perfilDados_screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoteme_app.model.AdotanteDados
import com.example.adoteme_app.model.AdotanteRequest
import com.example.adoteme_app.perfil.presentation.utils.components.DatePickerFieldToModal
import com.example.adoteme_app.perfil.presentation.utils.components.InputForm
import com.google.gson.GsonBuilder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilDadosScreen(onBack: () -> Unit, adotanteDados: AdotanteDados?) {
    var fieldNome by remember { mutableStateOf(adotanteDados?.nome ?: "") }
    var fieldEmail by remember { mutableStateOf(adotanteDados?.email ?: "") }
    var fieldCelular by remember { mutableStateOf(adotanteDados?.telefone ?: "") }
    var fieldDataNascimento by remember { mutableStateOf("") }
    var fieldCep by remember { mutableStateOf(adotanteDados?.cep ?: "") }
    var fieldEstado by remember { mutableStateOf(adotanteDados?.endereco?.estado ?: "") }
    var fieldCidade by remember { mutableStateOf(adotanteDados?.endereco?.cidade ?: "") }

    val actionColor = Color(red = 255, green = 166, blue = 7)

    Scaffold(
        topBar = {
            TopAppBar (
                title = { Text("MEUS DADOS", fontWeight = FontWeight.Bold) },
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
        Box(
            modifier = Modifier.padding(horizontal = 21.dp)
        ) {
            LazyColumn (
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {HorizontalDivider()}
                item { InputForm(fieldNome, "Nome Completo", KeyboardType.Text, onValueChange = { fieldNome = it }) }
                item {InputForm(fieldEmail, "E-mail", KeyboardType.Email, onValueChange = { fieldEmail = it})}
                item {InputForm(fieldCelular, "DDD + Celular", KeyboardType.Phone, onValueChange = { fieldCelular = it})}
                item {DatePickerFieldToModal(label = "Data de Nascimento", selectedDate = fieldDataNascimento, onDateSelected = { fieldDataNascimento = it}) }
                item {InputForm(fieldCep, "CEP", KeyboardType.Text, onValueChange = { fieldCep = it })}
                item {InputForm(fieldEstado, "Estado", KeyboardType.Text, onValueChange = { fieldEstado = it })}
                item {InputForm(fieldCidade, "Cidade", KeyboardType.Text, onValueChange = { fieldEstado = it })}
                item {
                    Button(
                        colors = ButtonColors(
                            containerColor = actionColor,
                            contentColor = Color.White,
                            disabledContentColor = Color.Transparent,
                            disabledContainerColor = Color.LightGray
                        ),
                        modifier = Modifier.fillMaxWidth( ),
                        onClick = { }
                    ) {
                        Text(
                            text = "Alterar Dados",
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
