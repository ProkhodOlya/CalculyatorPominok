package com.calculyatorpominok.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import javax.inject.Inject


class ThemeRepository @Inject private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("config-pref-theme", MODE_PRIVATE)

    fun setTheme(theme: Int) {
        val sharedPrefEditor = sharedPreferences.edit()
        sharedPrefEditor?.putInt(SAVED_THEME, theme)
        sharedPrefEditor?.apply()
    }

    fun getTheme(): Int {
        return sharedPreferences.getInt(SAVED_THEME, -1)
    }

    companion object {
        const val SAVED_THEME = "savedTheme"
        private var instance: ThemeRepository? = null
        fun getInstance(context: Context): ThemeRepository {
            if(instance == null) {
                instance = ThemeRepository(context)
            }
            return instance ?: ThemeRepository(context)
        }
    }
}