package com.calculyatorpominok

import android.app.Application
import android.content.Context
import com.calculyatorpominok.di.AppComponent
import com.calculyatorpominok.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.create()
    }

    companion object {
        private lateinit var instance: App
        fun getContext(): Context {
            return instance.applicationContext
        }
    }
}

val Context.appComponent: AppComponent
    get() = when (this){
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }