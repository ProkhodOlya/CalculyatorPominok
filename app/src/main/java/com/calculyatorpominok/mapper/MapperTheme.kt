package com.calculyatorpominok.mapper

import com.calculyatorpominok.utils.ThemeValue

fun Int.mapToTheme(): ThemeValue? =
    when (this) {
        ThemeValue.AUTO_THEME.value -> ThemeValue.AUTO_THEME
        ThemeValue.LIGHT_THEME.value -> ThemeValue.LIGHT_THEME
        ThemeValue.DARK_THEME.value -> ThemeValue.DARK_THEME
        else -> null
    }