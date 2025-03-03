package com.example.adoteme_app.navigation.presentation.navi_drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoteme_app.home.presentation.home_screen.HomeContent
import com.example.adoteme_app.home.presentation.home_screen.HomeViewState
import com.example.adoteme_app.navigation.presentation.utils.InternalRoutes
import kotlinx.coroutines.CoroutineScope

@Composable
fun NaviDrawer(
    drawerState: DrawerState,
    scope: CoroutineScope,
    navController: NavController
) {
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(175.dp)
                            .background(color = Color(
                                red = 255,
                                green = 197,
                                blue = 94
                            )),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier.size(100.dp),
                                imageVector = Icons.Filled.AccountCircle,
                                contentScale = ContentScale.Crop,
                                contentDescription = null
                            )
                            Text(
                                "Olá, usuário!",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }

                    }

                    Spacer(Modifier.height(32.dp))

                    NavigationDrawerItem(
                        label = { Text(text = "Animais")},
                        selected = false,
                        onClick = {
                            navController.navigate(InternalRoutes.Pets.route)
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Doações")},
                        selected = false,
                        onClick = {
                            navController.navigate(InternalRoutes.Doacoes.route)
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Ongs")},
                        selected = false,
                        onClick = {
                            navController.navigate(InternalRoutes.Ongs.route)
                        }
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        HomeContent(HomeViewState(), drawerState, scope)
    }
}