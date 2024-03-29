package com.work.toy_zigzag

import android.app.Application
import android.content.Context
import com.work.toy_zigzag.di.*
import com.work.toy_zigzag.util.SharedPreferences
import com.work.toy_zigzag.util.appExecutorsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        prefs = SharedPreferences(applicationContext)
        startKOIN()
    }

    private fun startKOIN() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    presenterModule,
                    repositoryModule,
                    sourceModule,
                    networkModule,
                    appExecutorsModule,
                    viewModelModule,
                    interactorModule
                )
            )
        }
    }

    fun context(): Context = applicationContext

    companion object {
        lateinit var instance: App
            private set
        lateinit var prefs: SharedPreferences
    }

}