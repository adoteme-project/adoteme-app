package com.example.adoteme_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.adoteme_app.R

@Composable
fun BannerCarrossel(imagens: List<Int>) {
    val pagerState = rememberPagerState { imagens.size }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth().height(200.dp)
        ) { page ->
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = imagens[page]),
                    contentDescription = "Banner ${page + 1}",
                    modifier = Modifier.fillMaxSize()
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

@Preview(showBackground = true)
@Composable
fun BannerCarrosselPreview() {
    val banners = listOf(
        R.drawable.achados,
        R.drawable.ongs,
        R.drawable.animais,
        R.drawable.doacoes
    )

    BannerCarrossel(imagens = banners)
}