package com.example.adoteme_app.auth.presentation.register_photo_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoteme_app.model.AdotanteViewModel
import com.example.adoteme_app.navigation.presentation.utils.RootRoutes
import com.example.adoteme_app.perfil.presentation.utils.components.ProfilePhotoPicker
import com.example.adoteme_app.ui.theme.MainColor
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationPhoneScreen(navController: NavController) {
    val viewModel: AdotanteViewModel = koinViewModel()

    val cadastroConcluido by viewModel.cadastroConcluido.collectAsState()

    LaunchedEffect (cadastroConcluido) {
        if (cadastroConcluido) {
            navController.navigate(RootRoutes.Login.route) {
                popUpTo(RootRoutes.UserFormRegistration.route) { inclusive = true }
            }
        }
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
        Box(modifier = Modifier.padding(horizontal = 21.dp)) {
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(21.dp)
            ) {
                item {
                    HorizontalDivider()
                }
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Foto de Perfil", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                        ProfilePhotoPicker("", sizeValue = 150.dp)
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                item {
                    Button(
                        colors = ButtonColors(
                            containerColor = MainColor,
                            contentColor = Color.White,
                            disabledContentColor = Color.Transparent,
                            disabledContainerColor = Color.LightGray
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {

                            // viewModel.cadastrarAdotante(adotanteCadastro, null)
                        }
                    ) {
                        Text(
                            text = "Finalizar Cadastro",
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