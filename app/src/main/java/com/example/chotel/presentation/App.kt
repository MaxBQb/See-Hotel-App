package com.example.chotel.presentation

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.chotel.data.di.DataDIModule
import com.example.chotel.presentation.di.PresentationDIModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.ksp.generated.*


@Suppress("unused") // Used by AndroidManifest.xml
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                PresentationDIModule().module,
                DataDIModule().module,
//                DomainDIModule().module, // Nothing to inject for now
            )
        }
    }
}