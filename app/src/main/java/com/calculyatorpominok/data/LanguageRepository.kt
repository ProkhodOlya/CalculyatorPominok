package com.calculyatorpominok.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.calculyatorpominok.presentation.models.TypeOfLanguage
import javax.inject.Inject


class LanguageRepository @Inject private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("config-pref-language", MODE_PRIVATE)

    fun setLanguage(language: String) {
        val sharedPrefEditor = sharedPreferences.edit()
        sharedPrefEditor?.putString(SAVED_LANGUAGE, language)
        sharedPrefEditor?.apply()
    }

    fun getLanguage(): String {
        return sharedPreferences.getString(SAVED_LANGUAGE, "") ?: TypeOfLanguage.AUTO.value
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