package com.example.adoteme_app.navigation.presentation.navi_drawer

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NaviDrawer() {
    ModalNavigationDrawer(
        drawerContent = {
            Text("Adoteme", modifier = Modifier.padding(32.dp))
            HorizontalDivider()
            NavigationDrawerItem(
                label = { Text(text = "Animais")},
                selected = false,
                onClick = {}
            )
        }
    ) {
        // Conteúdo da página
    }
}