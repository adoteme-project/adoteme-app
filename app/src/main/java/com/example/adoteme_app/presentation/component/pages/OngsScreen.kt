package com.example.adoteme_app.presentation.component.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.adoteme_app.R
import com.example.adoteme_app.presentation.component.OngCard
import com.example.adoteme_app.presentation.component.SearchBar
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import com.example.adoteme_app.presentation.viewmodel.OngViewModel


@Composable
fun OngsScreen(navController: NavController, viewModel: OngViewModel = koinViewModel()) {
    val ongs by viewModel.ongs.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
            Image(
                painter = painterResource(R.drawable.ongs),
                contentDescription = "Seção de Ongs",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        item { SearchBar() }
        items(ongs) { ong ->
            Spacer(modifier = Modifier.height(16.dp))
            OngCard(
                ong = ong,
                navController = navController
            )
        }
    }
}