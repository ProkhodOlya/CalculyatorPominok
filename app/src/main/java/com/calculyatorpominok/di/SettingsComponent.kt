package com.calculyatorpominok.di

import android.content.Context
import com.calculyatorpominok.App
import com.calculyatorpominok.presentation.screens.SettingsFragment
import dagger.Component
import dagger.Module
import dagger.Provides


@Component(modules = [SettingsModule::class])
interface SettingsComponent {
    fun inject(settingsFragment: SettingsFragment)

}

@Module
object SettingsModule {
    @Provides
    fun provideContext(): Context {
        return App.getContext()
    }
}