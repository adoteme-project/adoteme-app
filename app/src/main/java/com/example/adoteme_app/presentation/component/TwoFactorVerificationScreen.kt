package com.example.adoteme_app.presentation.component

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.adoteme_app.MainActivity
import com.example.adoteme_app.R
import com.example.adoteme_app.auth.presentation.login_screen.LoginState
import com.example.adoteme_app.auth.presentation.login_screen.LoginViewModel
import com.example.adoteme_app.data.repository.PerfilRepository
import com.example.adoteme_app.interfaces.AdotanteApiService
import com.example.adoteme_app.network.RetrofitInstance
import org.koin.androidx.compose.koinViewModel

@Composable
fun TwoFactorVerificationScreen(
    email: String,
    viewModel: LoginViewModel = koinViewModel(),
) {
    val context = LocalContext.current

    var otp by remember { mutableStateOf("") }
    val token by viewModel.token.collectAsState()
    val userId by viewModel.userId.collectAsState()
    val loginState by viewModel.loginState.collectAsState()


    LaunchedEffect (loginState) {
        if (loginState is LoginState.Success) {
            if (token.isNotBlank()) {
                context.getSharedPreferences("auth", Context.MODE_PRIVATE)
                    .edit()
                    .putString("token", token)
                    .putLong("userId", userId)
                    .apply()

                val perfilRepository = PerfilRepository(context)
                perfilRepository.salvarToken(token)

                val adotanteApiService = RetrofitInstance.retrofit.create(AdotanteApiService::class.java)

                try {
                    val adotante = adotanteApiService.getDadosAdotante(userId)
                    perfilRepository.salvarAdotante(adotante)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                context.startActivity(Intent(context, MainActivity::class.java))
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text("Digite o código enviado para seu e-mail")

        Spacer(Modifier.height(32.dp))

        OutlinedTextField(
            value = otp,
            onValueChange = { otp = it },
            label = { Text("Código OTP") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFFA607),
                unfocusedBorderColor = Color(0xFFFFA607)
            )
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { viewModel.validarOtp(email, otp) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFA607),
                contentColor = Color.White
            )
        ) {
            Text("Entrar")
        }

        if (loginState is LoginState.Error) {
            Text("Código inválido ou erro ao validar", color = Color.Red)
        }
    }

}