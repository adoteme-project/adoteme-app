package com.example.adoteme_app.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.adoteme_app.R

@Composable
fun FilterSidebar(isOpen: Boolean, onClose: () -> Unit) {
    AnimatedVisibility(visible = isOpen) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(300.dp)
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Adicionar Filtros", fontWeight = FontWeight.Bold)
                    Text(
                        text = "Limpar Tudo",
                        color = Color(0xFFA9B949),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {  }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))


                SearchLocationField()
                Spacer(modifier = Modifier.height(16.dp))


                FilterOption(title = "Tamanho", options = listOf("Pequeno", "Médio", "Grande", "Todos os tamanhos"), initiallyExpanded = true)
                FilterOption(title = "Espécie", options = listOf("Cachorro", "Gato", "Todas as espécies"), initiallyExpanded = true)
                FilterOption(title = "Sexo", options = listOf("Fêmea", "Macho", "Todos os sexos"), initiallyExpanded = true)

                Spacer(modifier = Modifier.height(16.dp))


                Button(
                    onClick = { onClose() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Fechar")
                }
            }
        }
    }
}

@Composable
fun FilterOption(title: String, options: List<String>, initiallyExpanded: Boolean = false) {
    var expanded by remember { mutableStateOf(initiallyExpanded) }
    var selectedOption by remember { mutableStateOf(options.firstOrNull() ?: "") }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title)
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = "Expand"
            )
        }

        if (expanded) {
            Column(modifier = Modifier.padding(start = 8.dp)) {
                options.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedOption = option
                            }
                            .padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = selectedOption == option,
                            onClick = { selectedOption = option }
                        )
                        Text(text = option)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchLocationField() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text("Localização...") },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Ícone de pesquisa",
                modifier = Modifier.size(24.dp)
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}
