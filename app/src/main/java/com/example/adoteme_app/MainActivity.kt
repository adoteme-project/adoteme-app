package com.example.adoteme_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.LaunchedEffect
import com.example.adoteme_app.ui.theme.AdotemeappTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdotemeappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   LoginScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    Text(
        text = "O Mobile"
    )
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_2)
@Composable
fun GreetingPreview() {
    AdotemeappTheme {
        LoginScreen(modifier = Modifier)
    }
}

@Composable
fun LoginScreen(modifier: Modifier) {
    var showForm by remember { mutableStateOf(false) }

    // Delay de 2000ms para exibir o formulário
    LaunchedEffect(Unit) {
        delay(4000)
        showForm = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_login_page),
            contentDescription = "Animal image",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Animação para subir o formulário
        val formOffSet by animateDpAsState(
            targetValue = if (showForm) 0.dp else 200.dp,
            animationSpec = tween(durationMillis = 500)
        )

        // Fazendo aparecer o formulario de login
        if (showForm) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LoginForm(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = formOffSet)
                )
            }
        }
    }
}

@Composable
fun LoginForm(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Digite seu e-mail") },
                supportingText = {
                    if (email.isEmpty()) {
                        Text(
                            text = "Precisa de um email",
                            color = Color.Red
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFFA607),
                    unfocusedBorderColor = Color(0xFFFFA607)
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Column(modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 8.dp)) {
            Text(
                text = "Senha",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    textAlign = TextAlign.Start,
                    color = Color(0xFF05112E),
                    fontFamily = FontFamily.SansSerif
                )
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Digite sua senha") },
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
            onClick = { /* Handle login */ },
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
            onClick = { /* Handle forgot password */ }
        ) {
            Text(
                text = "Esqueci minha senha",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier)

        TextButton(
            onClick = { /* Handle create account */ }
        ) {
            Text(
                text = "Criar conta",
                fontSize = 14.sp
            )
        }
    }
}