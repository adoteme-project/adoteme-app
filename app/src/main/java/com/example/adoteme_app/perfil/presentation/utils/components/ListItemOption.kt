package com.example.adoteme_app.perfil.presentation.utils.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.adoteme_app.model.AdotanteDados
import com.example.adoteme_app.model.PerfilViewModel
import com.example.adoteme_app.navigation.presentation.utils.InternalRoutes
import com.google.gson.Gson
import com.google.gson.GsonBuilder

@Composable
fun ListItemOption(text: String, navController: NavHostController,
                   route: String, adotanteDados: AdotanteDados?) {

    val gson: Gson = GsonBuilder().create()
    val adotanteDadosJson = gson.toJson(adotanteDados)

    Box(
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set("adotanteDados", adotanteDadosJson)

                navController.navigate(route) {
                    popUpTo(InternalRoutes.Profile.route) {
                        saveState = true
                    }
                } },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = text, fontSize = 16.sp)
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "Avançar"
            )
        }
    }
    HorizontalDivider()
    Spacer(modifier = Modifier.height(12.dp))
}