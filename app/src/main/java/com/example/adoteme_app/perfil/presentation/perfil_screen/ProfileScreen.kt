package com.example.adoteme_app.perfil.presentation.perfil_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.adoteme_app.navigation.presentation.utils.InternalRoutes
import com.example.adoteme_app.perfil.presentation.utils.components.ListItemOption
import com.example.adoteme_app.perfil.presentation.utils.components.ProfilePhotoPicker

@Composable
fun ProfileScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(21.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfilePhotoPicker()
            Text(text = "Adotante", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
        ListItemOption(
            text = "Meus Dados",
            navController =  navHostController,
            InternalRoutes.ProfileData.route
        )
        ListItemOption(
            text = "Meus Formulário",
            navController = navHostController,
            InternalRoutes.ProfileForm.route
        )
        ListItemOption(
            text = "Minhas Aplicações",
            navController = navHostController,
            InternalRoutes.ProfileAplicacoes.route
        )
    }
}