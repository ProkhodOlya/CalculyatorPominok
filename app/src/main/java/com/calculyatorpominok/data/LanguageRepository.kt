package com.calculyatorpominok.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class LanguageRepository private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("config-pref-theme", MODE_PRIVATE)

    fun setLanguage(language: Int) {
        val sharedPrefEditor = sharedPreferences.edit()
        sharedPrefEditor?.putInt(SAVED_LANGUAGE, language)
        sharedPrefEditor?.apply()
    }

    fun getLanguage(): Int {
        return sharedPreferences.getInt(SAVED_LANGUAGE, -1)
    }

    companion object {
        const val SAVED_LANGUAGE = "savedLanguage"
        private var instance: LanguageRepository? = null
        fun getInstance(context: Context): LanguageRepository {
            if(instance == null) {
                instance = LanguageRepository(context)
            }
            return instance ?: LanguageRepository(context)
        }
    }
}