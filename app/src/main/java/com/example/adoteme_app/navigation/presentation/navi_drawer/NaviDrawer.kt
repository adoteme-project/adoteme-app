package com.example.adoteme_app.navigation.presentation.navi_drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.adoteme_app.navigation.presentation.utils.InternalRoutes
import com.example.adoteme_app.navigation.presentation.utils.NavDrawerItem
import com.example.adoteme_app.navigation.presentation.utils.RootRoutes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun NaviDrawerLayout (
    drawerState: DrawerState,
    scope: CoroutineScope,
    navController: NavController
) {
    val items = listOf(
        NavDrawerItem.Home,
        NavDrawerItem.Pets,
        NavDrawerItem.Ongs,
        NavDrawerItem.Favoritos,
    )

    val mainColor = Color(
        red = 255,
        green = 197,
        blue = 94
    )

    val blueColor = Color(
        red = 76,
        green = 142,
        blue = 181
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(175.dp)
                .background(color = mainColor, shape = RoundedCornerShape(bottomEnd = 45.dp)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
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
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }

        Spacer(Modifier.height(32.dp))

        items.forEach { item ->
            DrawerItem(item = item, onItemClick = {
                navController.navigate(item.route) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                scope.launch {
                    drawerState.close()
                }
            })
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.padding(start = 12.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Button(
                colors = ButtonColors(
                    containerColor = blueColor,
                    contentColor = Color.White,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.LightGray
                ),
                onClick = {
                    navController.navigate(InternalRoutes.PetsInfo.route) {
                        popUpTo(InternalRoutes.Home.route) {
                            inclusive = true
                        }
                    }
                    scope.launch {
                        drawerState.close()
                    }
                }
            ) {
                Text(
                    text = "Entrar",
                    fontSize = 18.sp,
                )
            }
            Button(
                colors = ButtonColors(
                    containerColor = mainColor,
                    contentColor = Color.White,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.LightGray
                ),
                onClick = {}
            ) {
                Text(
                    text = "Cadastrar",
                    fontSize = 18.sp,
                )
            }
        }

    }
}