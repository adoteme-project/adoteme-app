package com.example.adoteme_app.navigation.presentation.navi_drawer

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.adoteme_app.MainActivity
import com.example.adoteme_app.R
import com.example.adoteme_app.WelcomeActivity
import com.example.adoteme_app.navigation.presentation.utils.InternalRoutes
import com.example.adoteme_app.navigation.presentation.utils.NavDrawerItem
import com.example.adoteme_app.navigation.presentation.utils.RootRoutes
import com.example.adoteme_app.ui.theme.BlueColor
import com.example.adoteme_app.ui.theme.MainColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun NaviDrawerLayout(
    drawerState: DrawerState,
    scope: CoroutineScope,
    mainNavController: NavController,  // Rotas externas
    nestedNavController: NavController  // Rotas internas
) {
    val items = listOf(
        NavDrawerItem.Home,
        NavDrawerItem.Pets,
        NavDrawerItem.Ongs,
        NavDrawerItem.Favoritos,
    )

    val contexto = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(175.dp)
                .background(color = MainColor, shape = RoundedCornerShape(bottomEnd = 45.dp)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(100.dp)
                        .clickable {
                            nestedNavController.navigate(InternalRoutes.Profile.route) {
                                popUpTo(InternalRoutes.Home.route) {
                                    saveState = true
                                }
                            }
                            scope.launch {
                                drawerState.close()
                            }
                        }.clip(CircleShape),
                    painter = painterResource(id = R.drawable.adotante_feliz),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
                Text(
                    "Olá, Adotante!",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        items.forEach { item ->
            DrawerItem(item = item, onItemClick = {
                when (item.route) {
                    InternalRoutes.Home.route,
                    InternalRoutes.Pets.route,
                    InternalRoutes.Ongs.route,
                    InternalRoutes.Favoritos.route -> {
                        nestedNavController.navigate(item.route) {
                            popUpTo(nestedNavController.graph.startDestinationId!!)
                            launchSingleTop = true
                        }
                    }
                    else -> {
                        mainNavController.navigate(item.route) {
                            popUpTo(RootRoutes.HomeSection.route)
                            launchSingleTop = true
                        }
                    }
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
                    containerColor = BlueColor,
                    contentColor = Color.White,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.LightGray
                ),
                onClick = {
                    val welcomeSection = Intent(contexto, WelcomeActivity::class.java)
                    contexto.startActivity(welcomeSection)
                }
            ) {
                Text(
                    text = "Entrar",
                    fontSize = 18.sp,
                )
            }
            Button(
                colors = ButtonColors(
                    containerColor = MainColor,
                    contentColor = Color.White,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.LightGray
                ),
                onClick = {
                    val welcomeSection = Intent(contexto, WelcomeActivity::class.java)
                    contexto.startActivity(welcomeSection)
                }
            ) {
                Text(
                    text = "Cadastrar",
                    fontSize = 18.sp,
                )
            }
        }
    }
}