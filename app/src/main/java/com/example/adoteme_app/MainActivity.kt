package com.example.adoteme_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.adoteme_app.auth.presentation.login_screen.module_auth.ModuleAuth
import com.example.adoteme_app.ui.theme.AdotemeappTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin{
            androidLogger()

            androidContext(this@MainActivity)
            modules(ModuleAuth)
        }

        setContent {
            AdotemeappTheme {
                MainApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AdotemeappTheme {
        MainApp()
    }
}