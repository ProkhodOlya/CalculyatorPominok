package com.calculyatorpominok.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class DateRepository private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("config-pref", MODE_PRIVATE)

    fun setSavedDate(date: Long) {
        val sharedPrefEditor = sharedPreferences.edit()
        sharedPrefEditor?.putLong(SAVED_DATE, date)
        sharedPrefEditor?.apply()
    }

    fun getSavedDate(): Long {
        val savedDate: Long = sharedPreferences.getLong(SAVED_DATE, -1)
        return savedDate
    }

    companion object {
        const val SAVED_DATE = "savedDate"
        private var instance: DateRepository? = null
        fun getInstance(context: Context): DateRepository {
            if(instance == null) {
                instance = DateRepository(context)
            }
            return instance ?: DateRepository(context)
        }
    }
}