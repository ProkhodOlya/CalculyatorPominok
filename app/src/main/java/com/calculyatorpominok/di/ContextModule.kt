package com.calculyatorpominok.di

import android.content.Context
import com.calculyatorpominok.App
import dagger.Module
import dagger.Provides

@Module
object ContextModule {
    @Provides
    fun provideContext(): Context {
        return App.getContext()
    }
}