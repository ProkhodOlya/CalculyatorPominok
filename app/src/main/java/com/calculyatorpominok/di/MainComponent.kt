package com.calculyatorpominok.di

import android.content.Context
import com.calculyatorpominok.App
import com.calculyatorpominok.presentation.main.MainFragment
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [MainModule::class])
interface MainComponent {
    fun inject(mainFragment: MainFragment)

}

@Module
object MainModule {
    @Provides
    fun provideContext(): Context {
        return App.getContext()
    }
}