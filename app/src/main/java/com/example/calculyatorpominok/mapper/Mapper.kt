package com.example.calculyatorpominok.mapper

import com.example.calculyatorpominok.R
import com.example.calculyatorpominok.utils.DayOfCommemoration
import com.example.calculyatorpominok.utils.FORTY_DAY_DESCRIPTION
import com.example.calculyatorpominok.utils.NINE_DAY_DESCRIPTION
import com.example.calculyatorpominok.utils.ONE_YEAR_DESCRIPTION
import com.example.calculyatorpominok.utils.SIX_MONTH_DESCRIPTION
import com.example.calculyatorpominok.utils.THREE_DAY_DESCRIPTION

fun Int.mapToDayOfCommemoration():DayOfCommemoration? =
    when (this) {
        DayOfCommemoration.THREE_DAY.value -> DayOfCommemoration.THREE_DAY
        DayOfCommemoration.NINE_DAY.value -> DayOfCommemoration.NINE_DAY
        DayOfCommemoration.FORTY_DAY.value -> DayOfCommemoration.FORTY_DAY
        DayOfCommemoration.SIX_MONTH.value -> DayOfCommemoration.SIX_MONTH
        DayOfCommemoration.ONE_YEAR.value -> DayOfCommemoration.ONE_YEAR
        else -> null
    }