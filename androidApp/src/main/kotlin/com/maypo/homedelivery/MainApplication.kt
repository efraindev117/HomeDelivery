package com.maypo.homedelivery

import android.app.Application
import com.maypo.homedelivery.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MainApplication)
            androidLogger(level = if (BuildConfig.DEBUG) Level.DEBUG else Level.NONE)
        }
    }
}