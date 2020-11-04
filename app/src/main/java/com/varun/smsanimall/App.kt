package com.varun.smsanimall

import android.app.Application
import com.varun.smsanimall.data.dataKoinModule
import com.varun.smsanimall.ui.di.uiKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(applicationContext)
            modules(listOf(appKoinModule, dataKoinModule, uiKoinModule))
        }
    }
}