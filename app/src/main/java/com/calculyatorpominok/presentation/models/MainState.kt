package com.calculyatorpominok.presentation.models

data class MainState(
    val dateOfDeath: Long = 0,
    val threeDateOfDeath: Long = 0,
    val nineDateOfDeath: Long = 0,
    val fortyDateOfDeath: Long = 0,
    val sixMonthDateOfDeath: Long = 0,
    val oneYearDateOfDeath: Long = 0,
    val typeOfTheme: TypeOfTheme,
    val typeOfLanguage: TypeOfLanguage
)