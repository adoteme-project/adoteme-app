package com.example.adoteme_app.home.presentation.home_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.adoteme_app.home.presentation.utils.components.AdotemeTopAppBar
import com.example.adoteme_app.navigation.presentation.navi_drawer.NaviDrawer
import kotlinx.coroutines.CoroutineScope

@Composable
internal fun HomeScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    NaviDrawer(drawerState, scope)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    state: HomeViewState,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AdotemeTopAppBar(drawerState, scope)
        }
    ) { innerPadding ->
        Text(
            text = "Texto",
            modifier = Modifier.padding(innerPadding)
        )
    }
}