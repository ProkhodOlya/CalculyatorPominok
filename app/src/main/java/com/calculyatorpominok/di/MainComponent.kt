package com.calculyatorpominok.di

import com.calculyatorpominok.presentation.main.MainFragment
import dagger.Component

@Component(modules = [ContextModule::class])
interface MainComponent {
    fun inject(mainFragment: MainFragment)
}