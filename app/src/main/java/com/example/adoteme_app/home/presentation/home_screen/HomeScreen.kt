package com.example.adoteme_app.home.presentation.home_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.adoteme_app.home.presentation.utils.components.AdotemeTopAppBar

@Composable
internal fun HomeScreen() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    state: HomeViewState
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AdotemeTopAppBar()
        }
    ) { innerPadding ->
        Text(
            text = "Texto",
            modifier = Modifier.padding(innerPadding)
        )
    }
}