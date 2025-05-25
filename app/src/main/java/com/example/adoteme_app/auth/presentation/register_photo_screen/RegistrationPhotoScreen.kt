package com.example.adoteme_app.auth.presentation.register_photo_screen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoteme_app.model.AdotanteRequest
import com.example.adoteme_app.model.AdotanteViewModel
import com.example.adoteme_app.navigation.presentation.utils.RootRoutes
import com.example.adoteme_app.perfil.presentation.utils.components.ProfilePhotoPicker
import com.example.adoteme_app.ui.theme.MainColor
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationPhoneScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
) {
    val context = LocalContext.current

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val adotanteInfoJson = navController.previousBackStackEntry
        ?.savedStateHandle
        ?.get<String>("adotanteWithForm")

    val gson = GsonBuilder().create()

    val adotanteInfo = remember(adotanteInfoJson) {
        adotanteInfoJson.let { gson.fromJson(it, AdotanteRequest::class.java) }
    }

    val viewModel: AdotanteViewModel = koinViewModel()
    val cadastroConcluido by viewModel.cadastroConcluido.collectAsState()
    val cadastroErro by viewModel.cadastroErro.collectAsState()
    val isLoading by viewModel.loading.collectAsState()

    LaunchedEffect(cadastroConcluido, cadastroErro) {
        if (cadastroConcluido) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar("Cadastro realizado com sucesso!")
            }
            navController.navigate(RootRoutes.Login.route) {
                popUpTo(RootRoutes.UserFormRegistration.route) { inclusive = true }
            }
        } else if (!cadastroConcluido && cadastroErro != null) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar("Erro ao cadastrar: $cadastroErro")
            }
        }
    }

    Scaffold (
        topBar = {
            TopAppBar (
                title = { Text("CRIAR CONTA", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(
                        onClick = {navController.popBackStack()}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Voltar"
                        )
                    }
                },
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 21.dp),
                verticalArrangement = Arrangement.spacedBy(21.dp)
            ) {
                item {
                    HorizontalDivider()
                }
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Foto de Perfil", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                        ProfilePhotoPicker(
                            "",
                            sizeValue = 150.dp,
                            imageUri = imageUri,
                            onImageSelected = { uri -> imageUri = uri }
                        )

                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                item {
                    Button(
                        colors = ButtonColors(
                            containerColor = MainColor,
                            contentColor = Color.White,
                            disabledContentColor = Color.Transparent,
                            disabledContainerColor = Color.LightGray
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            val compressedFile = imageUri?.let { compressImage(context, it) }

                            Log.i("Forms", "Adotante Info Json: $adotanteInfo")

                            viewModel.cadastrarAdotante(adotanteInfo, compressedFile)
                        }
                    ) {
                        Text(
                            text = "Finalizar Cadastro",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White

                        )
                    }
                }
            }
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MainColor
                    )
                }
            }
        }
    }
}

fun compressImage(context: Context, uri: Uri): File {
    val bitmap = if (Build.VERSION.SDK_INT < 28) {
        MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
    } else {
        val source = ImageDecoder.createSource(context.contentResolver, uri)
        ImageDecoder.decodeBitmap(source)
    }

    val compressedFile = File.createTempFile("profile_photo_compressed", ".jpg", context.cacheDir)
    val outputStream = FileOutputStream(compressedFile)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 60, outputStream) // qualidade entre 0-100
    outputStream.flush()
    outputStream.close()

    return compressedFile
}