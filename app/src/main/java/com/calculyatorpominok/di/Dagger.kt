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

    val mainViewModel: MainViewModel
}

@Module
object AppModule {

    @Provides
    fun provideMainViewModel(
        dateRepository: DateRepository,
        themeRepository: ThemeRepository,
        languageRepository: LanguageRepository
    ): MainViewModel {
        return MainViewModel(
            dateRepository = dateRepository,
            themeRepository = themeRepository,
            languageRepository = languageRepository
        )
    }

    @Provides
    fun provideDateRepository(context: Context): DateRepository {
        return DateRepository.getInstance(context = context)
    }

    @Provides
    fun provideThemeRepository(context: Context): ThemeRepository {
        return ThemeRepository.getInstance(context = context)
    }

    @Provides
    fun provideLanguageRepository(context: Context): LanguageRepository {
        return LanguageRepository.getInstance(context = context)
    }

    @Provides
    fun provideContext(): Context {
        return App.getContext()
    }

}