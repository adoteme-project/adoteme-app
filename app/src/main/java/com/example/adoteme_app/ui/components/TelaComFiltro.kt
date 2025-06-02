package com.example.adoteme_app.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.adoteme_app.model.FiltrosSelecionados

@Composable
fun <T> TelaComFiltro(
    titulo: String,
    listaOriginal: List<T>,
    navController: NavController,
    aplicarFiltro: (T, FiltrosSelecionados) -> Boolean,
    exibirGrid: @Composable (animaisFiltrados: List<T>) -> Unit
) {
    var filtrosAbertos by remember { mutableStateOf(false) }
    var filtrosSelecionados by remember { mutableStateOf(FiltrosSelecionados()) }

    val filtrados = listaOriginal.filter { aplicarFiltro(it, filtrosSelecionados) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(titulo, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    FilterButton { filtrosAbertos = true }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                exibirGrid(filtrados)
            }

        }

        if (filtrosAbertos) {
            AnimatedVisibility(
                visible = filtrosAbertos,
                enter = slideInHorizontally(initialOffsetX = { it }),
                exit = slideOutHorizontally(targetOffsetX = { it })
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .zIndex(1f)
                ) {
                    FiltroSidebar(
                        filtros = filtrosSelecionados,
                        onFiltroChange = { filtrosSelecionados = it },
                        onLimparTudo = { filtrosSelecionados = FiltrosSelecionados() },
                        onFechar = { filtrosAbertos = false }
                    )
                }
            }
        }
    }
}
