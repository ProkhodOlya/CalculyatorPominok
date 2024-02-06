package com.calculyatorpominok.di

import android.content.Context
import com.calculyatorpominok.App
import com.calculyatorpominok.presentation.details.DetailsFragment
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [DetailsModule::class])
interface DetailsComponent {
    fun inject(detailsFragment: DetailsFragment)

}

@Module
object DetailsModule {
    @Provides
    fun provideContext(): Context {
        return App.getContext()
    }
}