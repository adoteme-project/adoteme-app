package com.example.adoteme_app.navigation.presentation.navi_drawer

import android.content.Context
import android.content.Intent
import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberAsyncImagePainter
import com.example.adoteme_app.R
import com.example.adoteme_app.WelcomeActivity
import com.example.adoteme_app.model.PerfilViewModel
import com.example.adoteme_app.navigation.presentation.utils.InternalRoutes
import com.example.adoteme_app.navigation.presentation.utils.NavDrawerItem
import com.example.adoteme_app.navigation.presentation.utils.RootRoutes
import com.example.adoteme_app.ui.theme.BlueColor
import com.example.adoteme_app.ui.theme.MainColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun NaviDrawerLayout(
    drawerState: DrawerState,
    scope: CoroutineScope,
    mainNavController: NavController,
    nestedNavController: NavController,
    userViewModel: PerfilViewModel
) {
    val contexto = LocalContext.current

    val adotante by userViewModel.adotanteDados.collectAsState()
    val token by userViewModel.token.collectAsState()

    val items = remember(token) {
        mutableListOf(
            NavDrawerItem.Home,
            NavDrawerItem.Pets,
            NavDrawerItem.Ongs,
        ).apply {
            if (!token.isNullOrBlank()) {
                add(NavDrawerItem.Favoritos)
            }
        }
    }

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
                            if(token != null) {
                                nestedNavController.navigate(InternalRoutes.Profile.route) {
                                    popUpTo(InternalRoutes.Home.route) {
                                        saveState = true
                                    }
                                }
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        }
                        .clip(CircleShape),
                    painter = rememberAsyncImagePainter(adotante?.urlFoto ?: R.drawable.adotante_feliz),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
                Text(
                    text = "Olá, ${adotante?.nome ?: "Adotante"}!",
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
                    InternalRoutes.Ongs.route -> {
                        nestedNavController.navigate(item.route) {
                            popUpTo(nestedNavController.graph.startDestinationId!!)
                            launchSingleTop = true
                        }
                    }
                    InternalRoutes.Favoritos.route -> {
                        nestedNavController.navigate(InternalRoutes.Favoritos.route) {
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

        if (token != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    colors = ButtonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.LightGray
                    ),
                    onClick = {
                        userViewModel.logout(contexto)
                        val welcomeSection = Intent(contexto, WelcomeActivity::class.java)
                        contexto.startActivity(welcomeSection)
                    }
                ) {
                    Text(text = "Logout", fontSize = 18.sp)
                }
            }
        } else {
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
}