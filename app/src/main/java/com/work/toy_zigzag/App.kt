package com.work.toy_zigzag

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.work.toy_zigzag.di.repositoryModule
import com.work.toy_zigzag.di.sourceModule
import com.work.toy_zigzag.di.presenterModule
import com.work.toy_zigzag.di.viewModelModule
import com.work.toy_zigzag.di.networkModule
import com.work.toy_zigzag.util.SharedPreferences
import com.work.toy_zigzag.util.appExecutorsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() , LifecycleObserver {

    override fun onCreate() {
        super.onCreate()
        instance = this
        prefs = SharedPreferences(applicationContext)
        startKOIN()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
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
                    viewModelModule
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

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun inBackground() {
        Toast.makeText(instance.context(), "In Background", Toast.LENGTH_SHORT).show()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun inForeground() {
        Toast.makeText(instance.context(), "In Foreground", Toast.LENGTH_SHORT).show()
    }
}