package com.calculyatorpominok.presentation.models

data class SettingsState(val typeOfTheme: TypeOfTheme, val typeOfLanguage: TypeOfLanguage)

enum class TypeOfTheme(val value: Int) {
    AUTO(0),
    DARK(1),
    LIGHT(2),
}
enum class TypeOfLanguage(val value: String) {
    AUTO(""),
    RUSSIAN("ru-RU"),
    ENGLISH("en-EN"),
}