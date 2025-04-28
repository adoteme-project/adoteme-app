package com.example.adoteme_app.perfil.presentation.utils.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoteme_app.model.AdotanteListaRequisicaoDto
import com.example.adoteme_app.navigation.presentation.utils.InternalRoutes
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AplicacoesCard(
    aplicacao: AdotanteListaRequisicaoDto,
    navController: NavController
) {

    val dataFormatada = try {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        LocalDateTime.parse(aplicacao.dataAplicacao).format(formatter)
    } catch (e: Exception) {
        aplicacao.dataAplicacao
    }

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
                    text = aplicacao.nomePet,
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
//                Image(
//                    painter = painterResource(id = logo),
//                    contentDescription = "Logo da ONG",
//                    modifier = Modifier
//                        .size(100.dp)
//                        .clip(CircleShape),
//                    contentScale = ContentScale.Crop
//                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Data da aplicação: $dataFormatada",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("Status:")
                            withStyle(style = SpanStyle(color = Color.Green)) {
                                append("Aceito")
                            }
                        },
                        lineHeight = 14.sp
                    )
                    Text(
                        text = "Motivo: ${aplicacao.motivo}",
                        fontSize = 12.sp
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        navController.navigate(InternalRoutes.PetsInfo.route) {
                            popUpTo(InternalRoutes.Home.route) {
                                saveState = true
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC55E)),
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .width(100.dp)
                        .height(30.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(text = "Saiba Mias", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
        }
    }
}

fun getRandomColor(): Color {
    val random = (0..255).random()
    return Color(
        red = random,
        green = (0..255).random(),
        blue = (0..255).random()
    )
}