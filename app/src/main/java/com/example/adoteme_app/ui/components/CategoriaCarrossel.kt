package com.example.adoteme_app.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.CircleShape
import com.example.adoteme_app.model.Categoria

@Composable
fun CategoriaCarrossel(
    categorias: List<Categoria>,
    onCategoriaSelecionada: (String) -> Unit
) {
    val listState = rememberLazyListState()
    var currentPage by remember { mutableStateOf(0) }

    LaunchedEffect(listState.firstVisibleItemIndex) {
        currentPage = listState.firstVisibleItemIndex
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LazyRow(state = listState, modifier = Modifier.fillMaxWidth()) {
            items(categorias.size) { index ->
                val categoria = categorias[index]

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(100.dp)
                        .clickable { onCategoriaSelecionada(categoria.nome) },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .shadow(4.dp, CircleShape)
                            .background(Color(0xFFDFF6FF))
                    ) {
                        Image(
                            painter = painterResource(id = categoria.imageRes),
                            contentDescription = categoria.nome,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(8.dp)
                        )
                    }
                    Text(text = categoria.nome, fontSize = 14.sp)
                }
            }
        }
    }
}

