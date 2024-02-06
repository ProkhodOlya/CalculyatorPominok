package com.calculyatorpominok.di

import android.content.Context
import com.calculyatorpominok.App
import com.calculyatorpominok.data.DateRepository
import com.calculyatorpominok.data.LanguageRepository
import com.calculyatorpominok.data.ThemeRepository
import com.calculyatorpominok.presentation.main.MainFragment
import com.calculyatorpominok.presentation.main.MainViewModel
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainFragment: MainFragment)

}

@Module
object AppModule {
    @Provides
    fun provideContext(): Context {
        return App.getContext()
    }

}