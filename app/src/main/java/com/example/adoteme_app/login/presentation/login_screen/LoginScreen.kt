package com.example.adoteme_app.login.presentation.login_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onNavigate: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.fillMaxWidth().padding(innerPadding)
        ) {
            Text(
                text = "Login",
                color = Color.Blue,
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.height(21.dp))
            Button(
                onClick = onNavigate
            ) {
                Text(text = "Realizar Login !")
            }
        }
    }
}