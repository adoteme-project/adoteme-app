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
import com.example.adoteme_app.auth.data.ProfileFormState
import com.example.adoteme_app.model.AdotanteDados
import com.example.adoteme_app.model.AdotanteRequest
import com.example.adoteme_app.model.PerfilViewModel
import com.example.adoteme_app.perfil.presentation.utils.components.DatePickerFieldToModal
import com.example.adoteme_app.perfil.presentation.utils.components.InputForm
import com.example.adoteme_app.perfil.presentation.utils.components.PasswordInputForm
import com.google.gson.GsonBuilder
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilDadosScreen(
    onBack: () -> Unit,
    adotanteDados: AdotanteDados?,
    perfilViewModel: PerfilViewModel = koinViewModel()
) {
    val profileState = remember {
        mutableStateOf(
            ProfileFormState(
                nome = adotanteDados?.nome ?: "",
                email = adotanteDados?.email ?: "",
                celular = adotanteDados?.telefone ?: "",
                dataNascimento = adotanteDados?.dataNascimeto ?: "",
                cep = adotanteDados?.endereco?.cep ?: "",
                estado = adotanteDados?.endereco?.estado ?: "",
                cidade = adotanteDados?.endereco?.cidade ?: "",
                numero = adotanteDados?.endereco?.numero ?: "",
            )
        )
    }

    val form = profileState.value
    val isEditing = remember { mutableStateOf(false) }

    val actionColor = Color(red = 255, green = 166, blue = 7)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MEUS DADOS", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Voltar",
                            tint = Color(red = 198, green = 214, blue = 104)
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(horizontal = 21.dp)
        ) {
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { HorizontalDivider() }

                item {
                    InputForm(
                        value = form.nome,
                        label = "Nome Completo",
                        inputType = KeyboardType.Text,
                        enabled = isEditing.value,
                        onValueChange = { newValue ->
                            profileState.value = form.copy(nome = newValue)
                        }
                    )
                }
                item {
                    InputForm(
                        value = form.email,
                        label = "E-mail",
                        inputType = KeyboardType.Email,
                        enabled = isEditing.value,
                        onValueChange = { newValue ->
                            profileState.value = form.copy(email = newValue)
                        }
                    )
                }
                item {
                    InputForm(
                        value = form.celular,
                        label = "DDD + Celular",
                        inputType = KeyboardType.Phone,
                        enabled = isEditing.value,
                        onValueChange = { newValue ->
                            profileState.value = form.copy(celular = newValue)
                        }
                    )
                }
                item {
                    DatePickerFieldToModal(
                        label = "Data de Nascimento",
                        selectedDate = form.dataNascimento,
                        onDateSelected = { newValue ->
                            profileState.value = form.copy(dataNascimento = newValue)
                        }
                    )
                }
                item {
                    InputForm(
                        value = form.cep,
                        label = "CEP",
                        inputType = KeyboardType.Text,
                        enabled = false,
                        onValueChange = { newValue ->
                            profileState.value = form.copy(cep = newValue)
                        }
                    )
                }
                item {
                    InputForm(
                        value = form.estado,
                        label = "Estado",
                        inputType = KeyboardType.Text,
                        enabled = false,
                        onValueChange = { newValue ->
                            profileState.value = form.copy(estado = newValue)
                        }
                    )
                }
                item {
                    InputForm(
                        value = form.cidade,
                        label = "Cidade",
                        inputType = KeyboardType.Text,
                        enabled = false,
                        onValueChange = { newValue ->
                            profileState.value = form.copy(cidade = newValue)
                        }
                    )
                }
                item {
                    InputForm(
                        value = form.numero,
                        label = "Numero",
                        inputType = KeyboardType.Number,
                        enabled = false,
                        onValueChange = { newValue ->
                            profileState.value = form.copy(numero = newValue)
                        }
                    )
                }
                item {
                    Button(
                        colors = ButtonColors(
                            containerColor = actionColor,
                            contentColor = Color.White,
                            disabledContentColor = Color.Transparent,
                            disabledContainerColor = Color.LightGray
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (isEditing.value) {
                                adotanteDados?.id?.let { id ->
                                    perfilViewModel.atualizarAdotante(
                                        id = id,
                                        form = profileState.value
                                    )
                                }
                            }
                            isEditing.value = !isEditing.value
                        }
                    ) {
                        Text(
                            text = if (isEditing.value) "Salvar Alterações" else "Alterar Dados",
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