package com.example.adoteme_app.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.adoteme_app.domain.model.OngResponseAllDto
import com.example.adoteme_app.domain.model.InternalRoutes


@Composable
fun OngCard(ong: OngResponseAllDto, navController: NavController) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDF6F0))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .background(getRandomColor(), shape = RoundedCornerShape(bottomStart = 12.dp))
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .fillMaxWidth(0.5f)
            ) {
                Text(
                    text = ong.nome,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = ong.imagem,
                    contentDescription = "Logo da ONG",
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Endereço: ${ong.endereco.rua}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
//                    Text(
//                        text = "Descrição: ${ong.descricao}",
//                        fontSize = 12.sp
//                    )
                }
            }


//            Text(
//                text = ong.distancia,
//                fontSize = 12.sp,
//                color = Color.Black,
//                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
//            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        navController.navigate(InternalRoutes.OngsInfo.withId(ong.id))
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC55E)),
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .width(60.dp)
                        .height(30.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(text = "Ver", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = { /* TODO */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA9B949)),
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .width(80.dp)
                        .height(30.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(text = "Doar", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
        }
    }
}