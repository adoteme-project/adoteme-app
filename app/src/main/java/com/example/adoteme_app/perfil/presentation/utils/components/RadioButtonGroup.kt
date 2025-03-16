package com.example.adoteme_app.perfil.presentation.utils.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RadioButtonGroup(question: String, radioOptions: List<String>) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = question, fontSize = 18.sp)
        Row {
            radioOptions.forEach { text ->
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null
                )
                Text(text = text, modifier = Modifier.padding(start = 16.dp))
                Spacer(modifier = Modifier.width(21.dp))
            }
        }
    }
}