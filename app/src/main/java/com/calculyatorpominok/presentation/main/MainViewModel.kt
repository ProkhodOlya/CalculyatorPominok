package com.calculyatorpominok.presentation.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.calculyatorpominok.data.DateRepository
import com.calculyatorpominok.data.LanguageRepository
import com.calculyatorpominok.data.ThemeRepository
import com.calculyatorpominok.mapper.mapToLanguage
import com.calculyatorpominok.mapper.mapToTypeOfTheme
import com.calculyatorpominok.presentation.models.MainState
import com.calculyatorpominok.presentation.models.TypeOfLanguage
import com.calculyatorpominok.presentation.models.TypeOfTheme
import java.util.Calendar
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainViewModel @Inject constructor(
    private val dateRepository: DateRepository,
    private val themeRepository: ThemeRepository,
    private val languageRepository: LanguageRepository
) : ViewModel() {
    private val _state: MutableStateFlow<MainState> = MutableStateFlow(
        MainState(
            typeOfTheme = TypeOfTheme.AUTO,
            typeOfLanguage = TypeOfLanguage.AUTO
        )
    )
    val state: StateFlow<MainState> = _state

    fun start() {
        val time = dateRepository.getSavedDate().let { savedDate ->
            if (savedDate > 0) {
                savedDate
            } else {
                System.currentTimeMillis()
            }
        }
        val theme = themeRepository.getTheme().mapToTypeOfTheme()
        val language = languageRepository.getLanguage().mapToLanguage()
        updateState(time, typeOfTheme = theme, typeOfLanguage = language)
    }

    private fun calculateDate(selection: Long, amount: Int, calendarField: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = selection
        calendar.add(calendarField, amount)
        return calendar.timeInMillis
    }

    fun onSelectDate(selection: Long) {
        dateRepository.setSavedDate(selection)
        updateState(
            selection,
            typeOfTheme = _state.value.typeOfTheme,
            typeOfLanguage = _state.value.typeOfLanguage
        )
    }

    private fun updateState(time: Long, typeOfTheme: TypeOfTheme, typeOfLanguage: TypeOfLanguage) {
        _state.update { currentState ->
            currentState.copy(
                dateOfDeath = calculateDate(time, 0, Calendar.DAY_OF_MONTH),
                threeDateOfDeath = calculateDate(time, THREE_DATE, Calendar.DAY_OF_MONTH),
                nineDateOfDeath = calculateDate(time, NINE_DATE, Calendar.DAY_OF_MONTH),
                fortyDateOfDeath = calculateDate(time, FORTY_DATE, Calendar.DAY_OF_MONTH),
                sixMonthDateOfDeath = calculateDate(time, SIXMONTH_DATE, Calendar.MONTH),
                oneYearDateOfDeath = calculateDate(time, ONEYEAR_DATE, Calendar.YEAR),
                typeOfTheme = typeOfTheme,
                typeOfLanguage = typeOfLanguage
            )
        }
    }

    companion object {
        private const val THREE_DATE = 3 - 1
        private const val NINE_DATE = 9 - 1
        private const val FORTY_DATE = 40 - 1
        private const val SIXMONTH_DATE = 6
        private const val ONEYEAR_DATE = 1

//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val dateRepository =
//                    DateRepository.getInstance((this[APPLICATION_KEY] as Application).applicationContext)
//                val themeRepository =
//                    ThemeRepository.getInstance((this[APPLICATION_KEY] as Application).applicationContext)
//                val languageRepository =
//                    LanguageRepository.getInstance((this[APPLICATION_KEY] as Application).applicationContext)
//                MainViewModel(
//                    dateRepository = dateRepository,
//                    themeRepository = themeRepository,
//                    languageRepository = languageRepository
//                )
//            }
//        }
    }
}