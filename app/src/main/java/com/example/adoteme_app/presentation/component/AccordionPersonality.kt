package com.example.adoteme_app.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.adoteme_app.domain.model.PersonalidadeDto

@Composable
fun AccordionPersonality(
    sections: List<AccordionSection>,
    personalidade: PersonalidadeDto,
    modifier: Modifier = Modifier
) {
    val collapsedState = remember(sections) { sections.map { true }.toMutableStateList() }
    Column (modifier) {
        sections.forEachIndexed { i, dataItem ->
            val collapsed = collapsedState[i]
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable {
                        collapsedState[i] = !collapsed
                    }
            ) {
                Icon(
                    Icons.Default.run {
                        if (collapsed)
                            KeyboardArrowDown
                        else
                            KeyboardArrowUp
                    },
                    contentDescription = "",
                    tint = Color.LightGray,
                )
                Text(
                    dataItem.title,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .weight(1f)
                )
            }
            HorizontalDivider()
            if (!collapsed) {
                dataItem.rows.forEach { row ->
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            row,
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                        )
                        RatingBarPersonality(
                            currentRating = when (row) {
                                "Energia: ${personalidade.energia}" -> personalidade.energia
                                "Sociabilidade: ${personalidade.sociabilidade}" -> personalidade.sociabilidade
                                "Tolerância: ${personalidade.tolerante}" -> personalidade.tolerante
                                "Obediência: ${personalidade.obediente}" -> personalidade.obediente
                                "Territorialidade: ${personalidade.territorial}" -> personalidade.territorial
                                "Inteligência: ${personalidade.inteligencia}" -> personalidade.inteligencia
                                else -> 0
                            },
                        )
                    }
                }
            }
        }
    }
}

data class AccordionSection(val title: String, val rows: List<String>)