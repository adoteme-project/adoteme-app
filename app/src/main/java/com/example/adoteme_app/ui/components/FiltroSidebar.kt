package com.example.adoteme_app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.adoteme_app.model.FiltrosSelecionados

@Composable
fun FiltroSidebar(
    filtros: FiltrosSelecionados,
    onFiltroChange: (FiltrosSelecionados) -> Unit,
    onLimparTudo: () -> Unit,
    onFechar: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Adicionar Filtros", fontWeight = FontWeight.Bold)
            Text(
                "Limpar Tudo",
                color = Color(0xFF4CAF50),
                modifier = Modifier.clickable { onLimparTudo() }
            )
            Text(
                "Fechar ✕",
                color = Color.Red,
                modifier = Modifier.clickable { onFechar() }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        FiltroGrupo(
            titulo = "Tamanho",
            opcoes = listOf("Pequeno", "Médio", "Grande", "Todos os Tamanhos"),
            selecionado = filtros.tamanho
        ) { selecionado ->
            onFiltroChange(filtros.copy(tamanho = selecionado))
        }

        FiltroGrupo(
            titulo = "Espécie",
            opcoes = listOf("Cachorro", "Gato", "Todas as espécies"),
            selecionado = filtros.especie
        ) { selecionado ->
            onFiltroChange(filtros.copy(especie = selecionado))
        }

        FiltroGrupo(
            titulo = "Sexo",
            opcoes = listOf("Fêmea", "Macho", "Todos os sexos"),
            selecionado = filtros.sexo
        ) { selecionado ->
            onFiltroChange(filtros.copy(sexo = selecionado))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onFechar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Aplicar Filtros")
        }
    }
}
