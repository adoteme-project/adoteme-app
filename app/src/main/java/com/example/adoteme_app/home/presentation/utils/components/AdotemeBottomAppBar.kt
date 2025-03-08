package com.example.adoteme_app.home.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AdotemeBottomAppBar() {

    val mainColor = Color(
        red = 255,
        green = 197,
        blue = 94
    )

    val secondaryColor = Color(
        red = 253,
        green = 246,
        blue = 240
    )

    BottomAppBar(
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
        containerColor = Color.Transparent,
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(color = secondaryColor, shape = RoundedCornerShape(50.dp))
                    .padding(horizontal = 20.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(21.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    colors = ButtonColors(
                        containerColor = mainColor,
                        contentColor = Color.White,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.LightGray
                    ),
                    modifier = Modifier.weight(1f),
                    onClick = {}
                ) {
                    Text(
                        text = "Doação",
                        fontWeight = FontWeight.Bold
                    )
                }
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favoritar",
                        tint = Color.Red
                    )
                }
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        Icons.Outlined.Person,
                        contentDescription = "Usuário",
                        tint = Color.Green
                    )
                }
            }
        }
    )
}