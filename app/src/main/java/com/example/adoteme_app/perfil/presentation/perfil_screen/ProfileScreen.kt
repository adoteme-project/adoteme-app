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
import com.example.adoteme_app.perfil.presentation.utils.components.ListItemOption
import com.example.adoteme_app.perfil.presentation.utils.components.ProfilePhotoPicker

@Composable
fun ProfileScreen() {
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
        ListItemOption(text = "Meus Dados")
        ListItemOption(text = "Meus Formulário")
        ListItemOption(text = "Minhas Aplicações")
    }
}