package com.calculyatorpominok

import android.app.Application
import android.content.Context
import com.yandex.mobile.ads.common.MobileAds

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        // Configure the user privacy data policy before init sdk
        MobileAds.initialize(this) {
            // now you can use ads
        }
    }

    companion object {
        private lateinit var instance: App
        fun getContext(): Context {
            return instance.applicationContext
        }
    }
}

//val Context.mainComponent: MainComponent
//    get() = when (this){
//        is App -> mainComponent
//        else -> this.applicationContext.mainComponent
//    }