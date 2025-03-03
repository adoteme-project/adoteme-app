package com.example.adoteme_app.home.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.adoteme_app.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdotemeTopAppBar(drawerState: DrawerState, scope: CoroutineScope) {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = { scope.launch { drawerState.open() } }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu Hamburger",
                    tint = Color(
                        red = 198,
                        green = 214,
                        blue = 104
                    )
                )
            }
        },
        actions = {
            Image(
                painter = painterResource(id = R.drawable.logo_adotme_nobg),
                contentDescription = "Logo",
                modifier = Modifier.size(42.dp)
            )
            Text(text = "adoteme", modifier = Modifier.padding(start =  8.dp))
        },
        title = { Text(text = "") }
    )
}