package com.viper.databasework

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * created by viper on 2021/6/4
 * desc
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@App)
            modules(module)
        }
    }
}