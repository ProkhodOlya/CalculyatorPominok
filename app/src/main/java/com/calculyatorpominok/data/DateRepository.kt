package com.calculyatorpominok.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class DateRepository {
    private var sharedPreferences: SharedPreferences? = null


    fun setSavedDate(context: Context, date: Long) {
        sharedPreferences = context.getSharedPreferences("config-pref", MODE_PRIVATE)
        val sharedPrefEditor = sharedPreferences?.edit()
        sharedPrefEditor?.putLong(SAVED_DATE, date)
        sharedPrefEditor?.apply()
    }

    fun getSavedDate(context: Context): Long {
        val sharedPreferences = context.getSharedPreferences("config-pref", MODE_PRIVATE)
        val savedDate: Long = sharedPreferences.getLong(SAVED_DATE, -1)
        return savedDate
    }

    companion object {
        const val SAVED_DATE = "savedDate"
    }
}