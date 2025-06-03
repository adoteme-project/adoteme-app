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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoteme_app.domain.model.RootRoutes
import com.example.adoteme_app.presentation.component.PasswordInputForm
import com.example.adoteme_app.presentation.viewmodel.NovaSenhaViewModel
import com.example.adoteme_app.ui.theme.ActionColor
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RedefinicaoNovaSenhaScreen(
    email: String,
    navController: NavController,
    viewModel: NovaSenhaViewModel = koinViewModel()
) {

    val senha = viewModel.senha
    val confirmar = viewModel.confirmarSenha
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
                Text(
                    text =  "Crie uma nova senha",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text =  "Crie uma senha forte e exclusiva para máxima segurança.")
                Spacer(modifier = Modifier.height(12.dp))
                PasswordInputForm(
                    senha,
                    "Senha",
                    onValueChange = { novaSenha -> viewModel.onSenhaChange(novaSenha) },
                    errorMessage = erro,
                    enabled = true
                )
                Spacer(modifier = Modifier.height(16.dp))
                BulletList()
                Spacer(modifier = Modifier.height(16.dp))
                PasswordInputForm(
                    confirmar,
                    "Confirmar Senha",
                    onValueChange = { novoConfirmar -> viewModel.onConfirmarSenhaChange(novoConfirmar) },
                    errorMessage = erro,
                    enabled = true
                )
                Spacer(modifier = Modifier.height(21.dp))
                Button(
                    onClick = {
                        viewModel.redefinirSenha(email) {
                            navController.navigate("login") {
                                popUpTo(RootRoutes.RedefinicaoSenha.route) { inclusive = true }
                            }
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
                    else Text("Redefinir senha")
                }
            }
        }
    }
}