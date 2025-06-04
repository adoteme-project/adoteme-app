package com.example.adoteme_app.presentation.component.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adoteme_app.presentation.component.InputForm
import com.example.adoteme_app.presentation.viewmodel.RedefinicaoSenhaViewModel
import com.example.adoteme_app.ui.theme.ActionColor
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RedefinicaoSenhaScreen(
    navController: NavController,
    viewModel: RedefinicaoSenhaViewModel = koinViewModel()
) {

    val email = viewModel.email
    val erro = viewModel.erro
    val isLoading = viewModel.isLoading

    Scaffold(
        topBar = {
            TopAppBar (
                title = { Text("ESQUECI MINHA SENHA", fontWeight = FontWeight.Bold) },
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
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Text(text =  "Insira o e-mail do usuário para a recuperação de senha.")
                Spacer(modifier = Modifier.height(12.dp))
                InputForm(
                    value = email,
                    "E-mail",
                    KeyboardType.Email,
                    onValueChange = { novoEmail -> viewModel.onEmailChange(novoEmail)},
                    errorMessage = erro
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        viewModel.enviarCodigo {
                            navController.navigate("verificar_codigo/${email}")
                        }
                    },
                    enabled = !isLoading,
                    colors = ButtonColors(
                        containerColor = ActionColor,
                        contentColor = Color.White,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.LightGray
                    ),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    if (isLoading) CircularProgressIndicator(modifier = Modifier.size(16.dp))
                    else Text("Continuar")
                }
            }
        }
    }
}