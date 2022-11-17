package com.wagnod.mentalme

import android.app.Application
import com.wagnod.mentalme.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(appModules)
        }
    }
}