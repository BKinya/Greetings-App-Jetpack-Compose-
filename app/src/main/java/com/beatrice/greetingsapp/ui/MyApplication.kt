package com.beatrice.greetingsapp.ui

import android.app.Application
import com.beatrice.greetingsapp.ui.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            AndroidLogger()
            androidContext(this@MyApplication)
            modules(
                appModules
            )
        }
    }
}