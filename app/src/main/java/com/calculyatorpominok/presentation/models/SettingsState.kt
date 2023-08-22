package com.calculyatorpominok.presentation.models

data class SettingsState(val typeOfTheme: TypeOfTheme)

enum class TypeOfTheme(val value: Int) {
    AUTO(0),
    DARK(1),
    LIGHT(2),
}