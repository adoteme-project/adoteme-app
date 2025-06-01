package com.example.adoteme_app.presentation.component.pages

import android.util.Patterns
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adoteme_app.auth.presentation.login_screen.LoginViewModel
import com.example.adoteme_app.perfil.presentation.utils.components.InputForm
import com.example.adoteme_app.ui.theme.ActionColor
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabilitarTwoFactorScreen(
    navController: NavController,
    viewModel: LoginViewModel = koinViewModel(),

) {
    val email = remember { mutableStateOf(viewModel.email) }
    var erro: String = ""

    Scaffold (
        topBar = {
            TopAppBar (
                title = { Text("HABILITAR 2FA", fontWeight = FontWeight.Bold) },
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
                Text(text =  "Insira o e-mail a qual seria enviado o cógido confirmação.")
                Spacer(modifier = Modifier.height(12.dp))
                InputForm(
                    value = email.value,
                    "E-mail",
                    KeyboardType.Email,
                    onValueChange = { novoEmail -> email.value = novoEmail},
                    errorMessage = erro
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        if(Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
                            viewModel.ativarTwoFactorAuth(email = email.value) {
                                navController.popBackStack()
                            }
                        } else {
                            erro = "E-mail inválido"
                        }
                    },
                    enabled = true,
                    colors = ButtonColors(
                        containerColor = ActionColor,
                        contentColor = Color.White,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.LightGray
                    ),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Habilitar Email")
                }
            }
        }

    }
}