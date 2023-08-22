package com.calculyatorpominok.presentation.screens

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.calculyatorpominok.data.ThemeRepository
import com.calculyatorpominok.mapper.mapToTypeOfTheme
import com.calculyatorpominok.presentation.models.SettingsState
import com.calculyatorpominok.presentation.models.TypeOfTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SettingsViewModel(private val themeRepository: ThemeRepository) : ViewModel() {
    private val _state: MutableStateFlow<SettingsState> = MutableStateFlow(SettingsState(TypeOfTheme.AUTO))
    val state: StateFlow<SettingsState> = _state

    fun start() {
        val theme = themeRepository.getTheme().mapToTypeOfTheme()
        updateState(typeOfTheme =  theme)
    }

    fun saveTheme (typeOfTheme: TypeOfTheme) {
        themeRepository.setTheme(typeOfTheme.value)
    }

    private fun updateState(typeOfTheme: TypeOfTheme) {
        _state.update { currentState ->
            currentState.copy(
                typeOfTheme = typeOfTheme

            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val themeRepository =
                    ThemeRepository.getInstance((this[APPLICATION_KEY] as Application).applicationContext)
                SettingsViewModel(
                    themeRepository = themeRepository
                )
            }
        }
    }
}