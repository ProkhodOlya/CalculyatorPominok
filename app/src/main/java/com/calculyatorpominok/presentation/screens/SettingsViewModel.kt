package com.calculyatorpominok.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.calculyatorpominok.data.LanguageRepository
import com.calculyatorpominok.data.ThemeRepository
import com.calculyatorpominok.mapper.mapToLanguage
import com.calculyatorpominok.mapper.mapToTypeOfTheme
import com.calculyatorpominok.presentation.models.SettingsState
import com.calculyatorpominok.presentation.models.TypeOfLanguage
import com.calculyatorpominok.presentation.models.TypeOfTheme
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SettingsViewModel(
    private val themeRepository: ThemeRepository,
    private val languageRepository: LanguageRepository
) : ViewModel() {
    private val _state: MutableStateFlow<SettingsState> =
        MutableStateFlow(SettingsState(TypeOfTheme.AUTO, TypeOfLanguage.AUTO))
    val state: StateFlow<SettingsState> = _state

    class Factory @Inject constructor(
        private val themeRepository: ThemeRepository,
        private val languageRepository: LanguageRepository
    ) : ViewModelProvider.Factory{

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(themeRepository, languageRepository) as T
        }
    }

    fun start() {
        val theme = themeRepository.getTheme().mapToTypeOfTheme()
        val language = languageRepository.getLanguage().mapToLanguage()
        updateState(typeOfTheme = theme, typeOfLanguage = language)
    }

    fun saveTheme(typeOfTheme: TypeOfTheme) {
        themeRepository.setTheme(typeOfTheme.value)
    }

    fun saveLanguage(typeOfLanguage: TypeOfLanguage) {
        languageRepository.setLanguage(typeOfLanguage.value)
    }

    private fun updateState(typeOfTheme: TypeOfTheme, typeOfLanguage: TypeOfLanguage) {
        _state.update { currentState ->
            currentState.copy(
                typeOfTheme = typeOfTheme,
                typeOfLanguage = typeOfLanguage
            )
        }
    }
//
//    companion object {
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val themeRepository =
//                    ThemeRepository.getInstance((this[APPLICATION_KEY] as Application).applicationContext)
//                val languageRepository =
//                    LanguageRepository.getInstance((this[APPLICATION_KEY] as Application).applicationContext)
//                SettingsViewModel(
//                    themeRepository = themeRepository,
//                    languageRepository = languageRepository
//                )
//            }
//        }
//    }
}