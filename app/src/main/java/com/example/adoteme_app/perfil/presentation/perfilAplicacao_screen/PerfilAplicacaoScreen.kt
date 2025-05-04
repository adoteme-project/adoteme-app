package com.example.adoteme_app.perfil.presentation.perfilAplicacao_screen

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoteme_app.R
import com.example.adoteme_app.perfil.data.AplicacoesData
import com.example.adoteme_app.perfil.presentation.utils.components.AplicacoesCard
import com.example.adoteme_app.pets.presentation.pet_info_screen.RequisicaoViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import com.example.adoteme_app.model.AdotanteViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilAplicacoScreen(
    navController: NavController,
    viewModel: AdotanteViewModel = koinViewModel()
) {
    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getLong("userId", 0L)

    val aplicacaoData by viewModel.aplicacaoData.collectAsState()

    LaunchedEffect(userId) {
        if (userId != 0L) {
            viewModel.carregarAplicacao(userId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
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
                    Text(
                        "Após a aprovação da adoção, você receberá um e-mail com todas " +
                                "as informações sobre os próximos passos do processo."
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                items(aplicacaoData) { aplicacao ->
                    AplicacoesCard(
                        aplicacao = aplicacao,
                        navController = navController
                    )
                }
            }
        }
    }
}