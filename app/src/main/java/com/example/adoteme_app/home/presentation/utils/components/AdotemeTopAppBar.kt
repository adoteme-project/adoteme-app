package com.example.adoteme_app.home.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.adoteme_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdotemeTopAppBar() {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu Hamburger"
                )
            }
        },
        actions = {
            Image(
                painter = painterResource(id = R.drawable.logo_adotme_nobg),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(42.dp)
            )
            Text(
                text = "adoteme"
            )
        },
        title = {
            Text(
                text = ""
            )
        }
    )
}