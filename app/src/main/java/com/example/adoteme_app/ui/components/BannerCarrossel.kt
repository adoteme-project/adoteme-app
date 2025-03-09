package com.example.adoteme_app.ui.components

import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.adoteme_app.R

@Composable
fun BannerCarrossel(imagens: List<Int>) {
    val context = LocalContext.current
    val pagerState = remember { mutableStateOf(0) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AndroidView(
            factory = { context ->
                ViewPager2(context).apply {
                    adapter = BannerAdapter(imagens)
                    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            super.onPageSelected(position)
                            pagerState.value = position
                        }
                    })
                }
            },
            modifier = Modifier.fillMaxWidth().height(200.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.Center) {
            repeat(imagens.size) { index ->
                val color = if (pagerState.value == index) Color(0xFFFFA000) else Color.LightGray
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

class BannerAdapter(private val imagens: List<Int>) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
        return BannerViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.imageView.setImageResource(imagens[position])
    }

    override fun getItemCount(): Int = imagens.size

    class BannerViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
}

@Preview(showBackground = true)
@Composable
fun BannerCarrosselPreview() {
    val banners = listOf(R.drawable.achados, R.drawable.ongs, R.drawable.animais, R.drawable.doacoes)
    BannerCarrossel(imagens = banners)
}
