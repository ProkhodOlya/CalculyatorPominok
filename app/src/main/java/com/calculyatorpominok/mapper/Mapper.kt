package com.calculyatorpominok.mapper

import com.calculyatorpominok.presentation.models.TypeOfLanguage
import com.calculyatorpominok.presentation.models.TypeOfTheme
import com.calculyatorpominok.utils.DayOfCommemoration

fun Int.mapToDayOfCommemoration(): DayOfCommemoration? =
    when (this) {
        DayOfCommemoration.THREE_DAY.value -> DayOfCommemoration.THREE_DAY
        DayOfCommemoration.NINE_DAY.value -> DayOfCommemoration.NINE_DAY
        DayOfCommemoration.FORTY_DAY.value -> DayOfCommemoration.FORTY_DAY
        DayOfCommemoration.SIX_MONTH.value -> DayOfCommemoration.SIX_MONTH
        DayOfCommemoration.ONE_YEAR.value -> DayOfCommemoration.ONE_YEAR
        else -> null
    }

fun Int.mapToTypeOfTheme(): TypeOfTheme =
    when (this) {
        TypeOfTheme.AUTO.value -> TypeOfTheme.AUTO
        TypeOfTheme.DARK.value -> TypeOfTheme.DARK
        TypeOfTheme.LIGHT.value -> TypeOfTheme.LIGHT
        else -> TypeOfTheme.AUTO
    }

fun String.mapToLanguage(): TypeOfLanguage =
    when (this) {
        TypeOfLanguage.AUTO.value -> TypeOfLanguage.AUTO
        TypeOfLanguage.RUSSIAN.value -> TypeOfLanguage.RUSSIAN
        TypeOfLanguage.ENGLISH.value -> TypeOfLanguage.ENGLISH
        else -> TypeOfLanguage.AUTO
    }
