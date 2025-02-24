package com.example.adoteme_app.navigation.presentation.navi_drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.adoteme_app.home.presentation.home_screen.HomeContent
import com.example.adoteme_app.home.presentation.home_screen.HomeViewState
import kotlinx.coroutines.CoroutineScope

@Composable
fun NaviDrawer(
    drawerState: DrawerState,
    scope: CoroutineScope
) {

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(32.dp))
                    Text("Usuário", modifier = Modifier.padding(16.dp))
                    HorizontalDivider()

                    NavigationDrawerItem(
                        label = { Text(text = "Animais")},
                        selected = false,
                        onClick = {}
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Doações")},
                        selected = false,
                        onClick = {}
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Ongs")},
                        selected = false,
                        onClick = {}
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        HomeContent(HomeViewState(), drawerState, scope)
    }
}