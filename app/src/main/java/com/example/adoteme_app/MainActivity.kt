package com.example.adoteme_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.adoteme_app.model.Categoria
import com.example.adoteme_app.ui.components.*
import com.example.adoteme_app.ui.theme.AdotemeappTheme
import com.example.adoteme_app.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdotemeappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var isSidebarOpen by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()) {
        Box {
            FilterButton { isSidebarOpen = true }
            FilterSidebar(isOpen = isSidebarOpen, onClose = { isSidebarOpen = false })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AdotemeappTheme {
        MainScreen()
    }
}
