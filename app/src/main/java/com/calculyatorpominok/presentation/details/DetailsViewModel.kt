package com.calculyatorpominok.presentation.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.calculyatorpominok.data.ResourceProvider
import com.calculyatorpominok.mapper.mapToDayOfCommemoration
import com.calculyatorpominok.presentation.models.DetailsState
import com.calculyatorpominok.utils.DayOfCommemoration
import com.example.calculyatorpominok.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DetailsViewModel() : ViewModel() {
    private val _state: MutableStateFlow<DetailsState> = MutableStateFlow(DetailsState(0, 0))
    val state: StateFlow<DetailsState> = _state

    fun start(dayOfCommemoration: Int) {
        _state.update { getDetailsState(dayOfCommemoration) }
    }

    private fun getDetailsState(dayOfCommemoration: Int): DetailsState =
        when (dayOfCommemoration.mapToDayOfCommemoration()) {
            DayOfCommemoration.THREE_DAY -> {
                DetailsState(
                    dayDateOfDeathCaption = R.string.date_of_death_3day_caption,
                    detailsDateOfDeath = R.string.three_day_description
                )
            }

            DayOfCommemoration.NINE_DAY -> {
                DetailsState(
                    dayDateOfDeathCaption = R.string.date_of_death_9day_caption,
                    detailsDateOfDeath = R.string.nine_day_description
                )
            }

            DayOfCommemoration.FORTY_DAY -> {
                DetailsState(
                    dayDateOfDeathCaption = R.string.date_of_deatg_40day_caption,
                    detailsDateOfDeath = R.string.forty_day_description
                )
            }

            DayOfCommemoration.SIX_MONTH -> {
                DetailsState(
                    dayDateOfDeathCaption = R.string.date_of_death_6month_caption,
                    detailsDateOfDeath = R.string.six_month_description
                )
            }

            DayOfCommemoration.ONE_YEAR -> {
                DetailsState(
                    dayDateOfDeathCaption = R.string.date_of_death_1year_caption,
                    detailsDateOfDeath = R.string.one_year_description
                )
            }

            null -> {
                DetailsState(
                    dayDateOfDeathCaption = R.string.error,
                    detailsDateOfDeath = R.string.error
                )
            }
        }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                DetailsViewModel()
            }
        }
    }
}