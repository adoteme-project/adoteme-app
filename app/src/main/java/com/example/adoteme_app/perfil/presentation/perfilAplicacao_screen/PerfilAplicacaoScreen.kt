package com.example.adoteme_app.perfil.presentation.perfilAplicacao_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoteme_app.R
import com.example.adoteme_app.perfil.data.AplicacoesData
import com.example.adoteme_app.perfil.presentation.utils.components.AplicacoesCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilAplicacoScreen(navController: NavController) {

    val aplicaoes = listOf(
        AplicacoesData("Noah","10/10/2024", "Atende todos os requisitos necesários", R.drawable.pet, Color(0xFF1E88E5))
    )

    Scaffold(
        topBar = {
            TopAppBar (
                title = { Text("") },
                navigationIcon = {
                    IconButton(
                        onClick = {navController.popBackStack()}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Voltar"
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            LazyColumn(
                modifier = Modifier.padding(12.dp)
            ) {
                item {
                    Text("Minhas aplicações", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Após a aprovação da adoção, você receberá um e-mail com todas " +
                            "as informações sobre os próximos passos do processo.")
                    Spacer(modifier = Modifier.height(12.dp))
                }

                items(aplicaoes) { aplicacao ->
                    AplicacoesCard(
                        nome = aplicacao.nome,
                        dataAplicacao = aplicacao.dataAplicacao,
                        logo = aplicacao.logo,
                        motivo = aplicacao.motivo,
                        categoriaColor = aplicacao.categoriaColor,
                        navController = navController
                    )
                }
            }
        }
    }

}