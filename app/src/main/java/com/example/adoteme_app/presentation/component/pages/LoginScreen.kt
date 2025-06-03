package com.example.adoteme_app.presentation.component.pages

import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.adoteme_app.presentation.activity.MainActivity
import com.example.adoteme_app.R
import com.example.adoteme_app.presentation.viewmodel.LoginState
import com.example.adoteme_app.presentation.viewmodel.LoginViewModel
import com.example.adoteme_app.data.repository.PerfilRepository
import com.example.adoteme_app.data.network.api.AdotanteApiService
import com.example.adoteme_app.domain.model.RootRoutes
import com.example.adoteme_app.presentation.viewmodel.AdotanteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import java.net.URLEncoder

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = koinViewModel(),
    adotanteViewModel: AdotanteViewModel = koinViewModel(),
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
) {
    val token by viewModel.token.collectAsState()
    val userId by viewModel.userId.collectAsState()
    val loginState by viewModel.loginState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(token, loginState) {
        when (loginState) {
            is LoginState.Loading -> {
                snackbarHostState.showSnackbar(
                    message = "Entrando...",
                    withDismissAction = false,
                    duration = SnackbarDuration.Indefinite
                )
            }
            is LoginState.Success -> {
                snackbarHostState.currentSnackbarData?.dismiss()
                if (token.isNotBlank()) {
                    context.getSharedPreferences("auth", Context.MODE_PRIVATE)
                        .edit()
                        .putString("token", token)
                        .putLong("userId", userId)
                        .apply()

                    val perfilRepository = PerfilRepository(context)
                    perfilRepository.salvarToken(token)

                    try {
                        val adotante = adotanteViewModel.getDadosAdotante(userId)
                        perfilRepository.salvarAdotante(adotante)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    context.startActivity(Intent(context, MainActivity::class.java))
                }
            }
            is LoginState.Require2FA -> {
                snackbarHostState.currentSnackbarData?.dismiss()
                navController.navigate("twoFactorVerification/${URLEncoder.encode(viewModel.email, "UTF-8")}")
            }
            is LoginState.Error -> {
                snackbarHostState.currentSnackbarData?.dismiss()
                snackbarHostState.showSnackbar("Erro ao fazer login, email ou senha inválidos")
            }
            else -> {
                snackbarHostState.currentSnackbarData?.dismiss()
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { innerPadding ->
        var showForm by remember { mutableStateOf(false) }


        // Delay de 2000ms para exibir o formulário
        LaunchedEffect(Unit) {
            delay(1000)
            showForm = true
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_login_page),
                contentDescription = "Animal image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            val formOffSet by animateDpAsState(
                targetValue = if (showForm) 0.dp else 200.dp,
                animationSpec = tween(durationMillis = 500)
            )

            if (showForm) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    LoginForm(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .offset(y = formOffSet),
                        navController = navController,
                        loginViewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun LoginForm(modifier: Modifier = Modifier, navController: NavHostController, loginViewModel: LoginViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .background(color = Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 16.dp)) {
            Text(
                text = "E-mail",
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                style = TextStyle(
                    textAlign = TextAlign.Start,
                    color = Color(0xFF05112E),
                    fontFamily = FontFamily.SansSerif
                )
            )

            Spacer(modifier = Modifier.height(2.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = false
                },
                isError = emailError,
                supportingText = {
                    if (emailError) Text("Campo obrigatório", color = Color.Red)
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFFA607),
                    unfocusedBorderColor = Color(0xFFFFA607)
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Senha",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    textAlign = TextAlign.Start,
                    color = Color(0xFF05112E),
                    fontFamily = FontFamily.SansSerif
                )
            )

            Spacer(modifier = Modifier.height(2.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = false
                },
                isError = passwordError,
                supportingText = {
                    if (passwordError) Text("A senha deve ter no mínimo 8 caracteres", color = Color.Red)
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFFA607),
                    unfocusedBorderColor = Color(0xFFFFA607)
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                emailError = email.isBlank()
                passwordError = password.isBlank() || password.length < 8

                if (!emailError && !passwordError) {
                    loginViewModel.email = email
                    loginViewModel.login(email, password)
                }
            },
            modifier = Modifier.fillMaxWidth().padding(start = 32.dp, end = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFA607),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Entrar",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = {
                navController.navigate(RootRoutes.RedefinicaoSenha.route) {
                    popUpTo(RootRoutes.Login.route) { inclusive = true }
                }
            }
        ) {
            Text(
                text = "Esqueci minha senha",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier)

        TextButton(
            onClick = {
                navController.navigate(RootRoutes.UserRegistration.route) {
                    popUpTo(RootRoutes.Login.route) { saveState = true }
                }
            }
        ) {
            Text(
                text = "Criar conta",
                fontSize = 14.sp
            )
        }
    }
}