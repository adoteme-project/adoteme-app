package com.example.adoteme_app

import android.app.Application
import com.example.adoteme_app.application.di.moduloPrincipal
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AdoteMeApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AdoteMeApp)
            modules(moduloPrincipal)
        }
    }
}
