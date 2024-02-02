package com.calculyatorpominok.data

import android.annotation.SuppressLint
import android.content.Context
import com.calculyatorpominok.utils.DayOfCommemoration
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class DataRepository private constructor(private val context: Context) {

    fun getDayOfCommemoration(dayOfCommemoration: DayOfCommemoration): DataDayOfCommemoration {
        val fileName = when (dayOfCommemoration) {
            DayOfCommemoration.THREE_DAY -> "dataDayOfCommemorationThree.json"
            DayOfCommemoration.NINE_DAY -> "dataDayOfCommemorationNine.json"
            DayOfCommemoration.FORTY_DAY -> "dataDayOfCommemorationForty.json"
            DayOfCommemoration.SIX_MONTH -> "dataDayOfCommemorationSixMonth.json"
            DayOfCommemoration.ONE_YEAR -> "dataDayOfCommemorationOneYear.json"
        }
        val jsonString: String = try {
            context.assets.open(fileName)
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ""
        }

        val dataDayOfCommemoration = object : TypeToken<DataDayOfCommemoration>() {}.type
        return Gson().fromJson(jsonString, dataDayOfCommemoration)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: DataRepository? = null
        fun getInstance(context: Context): DataRepository {
            if (instance == null) {
                instance = DataRepository(context)
            }
            return instance ?: DataRepository(context)
        }
    }
}