package com.example.adoteme_app.presentation.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.adoteme_app.presentation.component.MainApp
import com.example.adoteme_app.ui.theme.AdotemeappTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdotemeappTheme {
                MainApp()
            }
        }
    }
}