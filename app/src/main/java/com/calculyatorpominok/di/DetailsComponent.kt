package com.calculyatorpominok.di

import com.calculyatorpominok.presentation.details.DetailsFragment
import dagger.Component

@Component(modules = [ContextModule::class])
interface DetailsComponent {
    fun inject(detailsFragment: DetailsFragment)

}

