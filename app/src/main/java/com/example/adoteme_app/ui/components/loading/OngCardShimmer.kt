package com.example.adoteme_app.ui.components.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun OngCardShimmer() {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            ShimmerEffect(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(30.dp)
                    .align(Alignment.End)
                    .clip(RoundedCornerShape(bottomStart = 12.dp))
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShimmerEffect(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    ShimmerEffect(
                        modifier = Modifier
                            .height(16.dp)
                            .fillMaxWidth(0.6f)
                            .clip(RoundedCornerShape(4.dp))
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ShimmerEffect(
                        modifier = Modifier
                            .height(14.dp)
                            .fillMaxWidth(0.4f)
                            .clip(RoundedCornerShape(4.dp))
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                ShimmerEffect(
                    modifier = Modifier
                        .width(60.dp)
                        .height(30.dp)
                        .clip(RoundedCornerShape(50))
                )

                Spacer(modifier = Modifier.width(8.dp))

                ShimmerEffect(
                    modifier = Modifier
                        .width(80.dp)
                        .height(30.dp)
                        .clip(RoundedCornerShape(50))
                )
            }
        }
    }
}