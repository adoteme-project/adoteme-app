package com.example.adoteme_app.presentation.component.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoteme_app.domain.model.InternalRoutes
import com.example.adoteme_app.domain.model.RootRoutes
import com.example.adoteme_app.ui.theme.ActionColor

@Composable
fun ConfiguracoesScreen(
    navController: NavController,
) {

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "Configurações",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(21.dp))

        Button (
            colors = ButtonColors(
                containerColor = ActionColor,
                contentColor = Color.White,
                disabledContentColor = Color.Transparent,
                disabledContainerColor = Color.LightGray
            ),
            onClick = {
                navController.navigate(InternalRoutes.HabilitarTwoFactor.route) {
                    popUpTo(RootRoutes.HomeSection.route)
                    launchSingleTop = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                "Habilitar 2FA",
                style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold)
            )
        }
    }
}