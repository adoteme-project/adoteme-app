package com.example.adoteme_app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun FiltroGrupo(titulo: String, opcoes: List<String>, selecionado: String, onSelecionar: (String) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(titulo, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        opcoes.forEach { opcao ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelecionar(opcao) }
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = selecionado == opcao,
                    onClick = { onSelecionar(opcao) }
                )
                Text(opcao)
            }
        }
    }
}
