package com.calculyatorpominok.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.MapKey
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import kotlin.reflect.KClass

class ViewModelModule {
    @MapKey
    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Module
    class ViewModelModule {
        @Provides
        fun provideViewModelFactory(
            providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val provider = providers[modelClass]
                        ?: providers.asIterable()
                            .firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
                        ?: throw IllegalArgumentException("Unknown ViewModel class: $modelClass")

                    @Suppress("UNCHECKED_CAST")
                    return provider.get() as T
                }
            }
        }
    }
}