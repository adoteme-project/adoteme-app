package com.example.adoteme_app.presentation.component

import android.Manifest
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.adoteme_app.ui.theme.ActionColor
import com.example.adoteme_app.ui.theme.MainColor
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ProfilePhotoPicker(
    profileUrl: String?,
    sizeValue: Dp = 100.dp,
    imageUri: Uri? = null,
    onImageSelected: (Uri) -> Unit
) {

    val permissionState = rememberPermissionState(Manifest.permission.READ_MEDIA_IMAGES)

    val imagePickerLauncher = rememberLauncherForActivityResult (
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { onImageSelected(it) }
    }

    Column (
        modifier = Modifier.padding(vertical = 21.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(sizeValue)
                .clip(CircleShape)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(ActionColor, MainColor)
                    )
                )
                .clickable {
                    if (permissionState.status.isGranted) {
                        imagePickerLauncher.launch("image/*")
                    } else {
                        println("Lançando permissão")
                        permissionState.launchPermissionRequest()
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            if (imageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = "Selected Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(sizeValue)
                        .clip(CircleShape)
                )
            } else {
                Image(
                    painter = rememberAsyncImagePainter(profileUrl ?: ""),
                    contentDescription = "Foto de Perfil",
                    modifier = Modifier.size(sizeValue),
                )
            }
        }
    }
}