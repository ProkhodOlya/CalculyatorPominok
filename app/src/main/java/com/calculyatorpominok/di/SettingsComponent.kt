package com.calculyatorpominok.di

import com.calculyatorpominok.presentation.screens.SettingsFragment
import dagger.Component


@Component(modules = [ContextModule::class])
interface SettingsComponent {
    fun inject(settingsFragment: SettingsFragment)

}

