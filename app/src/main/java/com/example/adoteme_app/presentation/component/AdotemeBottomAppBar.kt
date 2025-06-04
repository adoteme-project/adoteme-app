package com.example.adoteme_app.presentation.component

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adoteme_app.presentation.activity.WelcomeActivity
import com.example.adoteme_app.presentation.viewmodel.PerfilViewModel
import com.example.adoteme_app.domain.model.InternalRoutes
import com.example.adoteme_app.ui.theme.GreenColor
import com.example.adoteme_app.ui.theme.MainColor

@Composable
fun AdotemeBottomAppBar(
    nestedNavController: NavController,  // Rotas internas
    userViewModel: PerfilViewModel
) {
    val token by userViewModel.token.collectAsState()
    val contexto = LocalContext.current

    val secondaryColor = Color(red = 253, green = 246, blue = 240)

    BottomAppBar(
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
        containerColor = Color.Transparent,
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(color = secondaryColor, shape = RoundedCornerShape(50.dp))
                    .padding(horizontal = 20.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(21.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    colors = ButtonColors(
                        containerColor = MainColor,
                        contentColor = Color.White,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.LightGray
                    ),
                    modifier = Modifier.weight(1f),
                    onClick = {
                        nestedNavController.navigate(InternalRoutes.Ongs.route) {
                            popUpTo(InternalRoutes.Home.route) {
                                saveState = true
                            }
                        }
                    }
                ) {
                    Text(
                        text = "Doação",
                        fontWeight = FontWeight.Bold
                    )
                }
                IconButton(
                    onClick = {
                        if(token != null) {
                            nestedNavController.navigate(InternalRoutes.Favoritos.route) {
                                popUpTo(InternalRoutes.Home.route) {
                                    saveState = true
                                }
                            }
                        } else {
                            val welcomeSection = Intent(contexto, WelcomeActivity::class.java)
                            contexto.startActivity(welcomeSection)
                        }
                    }
                ) {
                    Icon(
                        Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favoritar",
                        tint = Color.Red
                    )
                }
                IconButton(
                    onClick = {
                        if(token != null) {
                            nestedNavController.navigate(InternalRoutes.Profile.route) {
                                popUpTo(InternalRoutes.Home.route) {
                                    saveState = true
                                }
                            }
                        } else {
                            val welcomeSection = Intent(contexto, WelcomeActivity::class.java)
                            contexto.startActivity(welcomeSection)
                        }
                    }
                ) {
                    Icon(
                        Icons.Outlined.Person,
                        contentDescription = "Usuário",
                        tint = GreenColor
                    )
                }
            }
        }
    )
}