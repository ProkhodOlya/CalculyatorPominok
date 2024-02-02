package com.calculyatorpominok

import android.app.Application
import android.content.Context

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: App
        fun getContext(): Context {
            return instance.applicationContext
        }
    }
}