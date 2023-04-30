package com.calculyatorpominok.mapper

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