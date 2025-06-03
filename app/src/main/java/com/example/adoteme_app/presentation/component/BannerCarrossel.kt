package com.example.adoteme_app.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.adoteme_app.domain.model.InternalRoutes

@Composable
fun BannerCarrossel(
    bannerRoutes: Map<Int, InternalRoutes>, // Recebe o mapa de rotas (objetos InternalRoutes)
    navController: NavController
) {
    // Extrai as imagens do mapa de rotas
    val imagens = bannerRoutes.keys.toList()
    val pagerState = rememberPagerState { imagens.size }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth().height(200.dp)
        ) { page ->
            val imageRes = imagens[page]
            // Obtém o objeto InternalRoutes correspondente à imagem atual
            val route = bannerRoutes[imageRes] ?: InternalRoutes.Home

            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Banner ${page + 1}",
                    modifier = Modifier.fillMaxSize().clickable {
                        // Navega para a rota correspondente ao banner clicado
                        navController.navigate(route.route) {
                            popUpTo(InternalRoutes.Home.route) {
                                saveState = true
                            }
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.Center) {
            repeat(imagens.size) { index ->
                val color = if (pagerState.currentPage == index) Color(0xFFFFA000) else Color.LightGray
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .padding(4.dp)
                        .background(color, shape = CircleShape)
                )
            }
        }
    }
}