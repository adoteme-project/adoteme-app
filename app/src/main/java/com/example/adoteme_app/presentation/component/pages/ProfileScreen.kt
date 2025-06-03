package com.example.adoteme_app.presentation.component.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.adoteme_app.presentation.viewmodel.PerfilViewModel
import com.example.adoteme_app.domain.model.InternalRoutes
import com.example.adoteme_app.presentation.component.ListItemOption
import com.example.adoteme_app.presentation.component.ProfilePhotoPicker

@Composable
fun ProfileScreen(navHostController: NavHostController, userViewModel: PerfilViewModel) {

    val adotante by userViewModel.adotanteDados.collectAsState()

    Column(
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(21.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfilePhotoPicker(adotante?.urlFoto, onImageSelected = {})
            Text(text = adotante?.nome ?: "Adotante", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
        ListItemOption(
            text = "Meus Dados",
            navController =  navHostController,
            InternalRoutes.ProfileData.route,
            adotanteDados = adotante
        )
        ListItemOption(
            text = "Meus Formulário",
            navController = navHostController,
            InternalRoutes.ProfileForm.route,
            adotanteDados = adotante
        )
        ListItemOption(
            text = "Minhas Aplicações",
            navController = navHostController,
            InternalRoutes.ProfileAplicacoes.route,
            adotanteDados = adotante
        )
    }
}