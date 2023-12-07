package com.calculyatorpominok.presentation.main

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.calculyatorpominok.data.DateRepository
import com.calculyatorpominok.presentation.models.MainState
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
import kotlinx.coroutines.flow.update

class MainViewModel(private val dateRepository: DateRepository) : ViewModel() {
    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state

    fun start() {
        val time = dateRepository.getSavedDate().let { savedDate ->
            if (savedDate > 0) {
                savedDate
            } else {
                System.currentTimeMillis()
            }
        }
        updateState(time)
    }

    private fun getDate(milliSeconds: Long, dateFormat: String?): String {
        //TODO лучше не использовать Locale.getDefault() - медленно работает
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }

    private fun calculateDate(selection: Long, amount: Int, calendarField: Int): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = selection
        calendar.add(calendarField, amount)
        val timeInMillis = calendar.timeInMillis
        return getDate(timeInMillis, DATE_FORMAT)
    }

    fun onSelectDate(selection: Long) {
        dateRepository.setSavedDate(selection)
        updateState(selection)
    }

    private fun updateState(time: Long) {
        _state.update { currentState ->
            currentState.copy(
                dateOfDeath = getDate(time, DATE_FORMAT_DEATH),
                threeDateOfDeath = calculateDate(time, THREE_DATE, Calendar.DAY_OF_MONTH),
                nineDateOfDeath = calculateDate(time, NINE_DATE, Calendar.DAY_OF_MONTH),
                fortyDateOfDeath = calculateDate(time, FORTY_DATE, Calendar.DAY_OF_MONTH),
                sixMonthDateOfDeath = calculateDate(time, SIXMONTH_DATE, Calendar.MONTH),
                oneYearDateOfDeath = calculateDate(time, ONEYEAR_DATE, Calendar.YEAR)
            )
        }
    }

    companion object {
        private const val DATE_FORMAT_DEATH = "dd MMMM yyyy"
        private const val DATE_FORMAT = "dd.MM.yyyy, EEEE"
        private const val THREE_DATE = 3 - 1
        private const val NINE_DATE = 9 - 1
        private const val FORTY_DATE = 40 - 1
        private const val SIXMONTH_DATE = 6
        private const val ONEYEAR_DATE = 1
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val dateRepository =
                    DateRepository.getInstance((this[APPLICATION_KEY] as Application).applicationContext)
                MainViewModel(
                    dateRepository = dateRepository
                )
            }
        }
    }
}