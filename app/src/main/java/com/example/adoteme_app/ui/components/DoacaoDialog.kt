package com.example.adoteme_app.ui.components

import android.content.ClipboardManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoteme_app.model.DadosBancariosDto
import com.example.adoteme_app.model.OngDadosBancariosAnimalDto
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext


@Composable
fun DoacaoDialog(
    onDismiss: () -> Unit, dadosBancariosDto: OngDadosBancariosAnimalDto
) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        buttons = {},
        title = null,
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("TED ou depósito em conta", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Banco ${dadosBancariosDto.banco}")
                Text("Agência: ${dadosBancariosDto.agencia}")
                Text("Conta-corrente: ${dadosBancariosDto.conta}")
                Text(dadosBancariosDto.nomeTitular)
                Text("CNPJ: ${dadosBancariosDto.cnpj}")

                Divider(modifier = Modifier.padding(vertical = 12.dp))

                Text("Doações via PIX", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("Favor utilize as informações abaixo para realizar sua doação.")
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = dadosBancariosDto.chavePix,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    val clipboardManager = LocalClipboardManager.current

                    Button(onClick = {
                        clipboardManager.setText(AnnotatedString(dadosBancariosDto.chavePix))
                        Toast.makeText(context, "Chave Pix copiada", Toast.LENGTH_SHORT).show()
                    }) {
                        Text("Copiar")
                    }
                }
            }
        },
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White
    )
}
