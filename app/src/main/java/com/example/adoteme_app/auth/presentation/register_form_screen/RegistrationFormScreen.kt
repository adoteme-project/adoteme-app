package com.example.adoteme_app.auth.presentation.register_form_screen

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoteme_app.navigation.presentation.utils.RootRoutes
import com.example.adoteme_app.perfil.presentation.utils.components.RadioButtonGroup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationFormScreen(navController: NavController) {
    val actionColor = Color(red = 255, green = 166, blue = 7)

    Scaffold (
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
                item { Text(
                    "Preencha o formulário de adoção abaixo.",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 12.dp)
                )
                }
                item {
                    RadioButtonGroup(
                        "Moram crianças na sua casa?",
                        radioOptions = listOf("Sim", "Não")
                    )
                }
                item {
                    RadioButtonGroup(
                        "Todos que moram com você estão de acordo com a adoção?",
                        radioOptions = listOf("Sim", "Não")
                    )
                }
                item {
                    RadioButtonGroup(
                        "O responsável pelo animal será você ou outra pessoa?",
                        radioOptions = listOf("Sim", "Não")
                    )
                }
                item {
                    RadioButtonGroup(
                        "Você mora em casa ou apartamento?",
                        radioOptions = listOf("Sim", "Não")
                    )
                }
                item {
                    RadioButtonGroup(
                        "Sua residência é telada?",
                        radioOptions = listOf("Sim", "Não")
                    )
                }
                item {
                    RadioButtonGroup(
                        "Sua casa tem portão alto?",
                        radioOptions = listOf("Sim", "Não")
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
                        modifier = Modifier.fillMaxWidth( ),
                        onClick = {
                            navController.navigate(RootRoutes.Login.route) {
                                popUpTo(RootRoutes.UserFormRegistration.route) { saveState = true }
                            }
                        }
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