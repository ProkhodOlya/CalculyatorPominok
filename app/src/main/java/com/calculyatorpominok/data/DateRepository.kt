package com.calculyatorpominok.data

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.calculyatorpominok.MainActivity


class DateRepository {
    private var sharedPreferences : SharedPreferences? = null
    fun setDate (mainActivity: MainActivity) {
        sharedPreferences = mainActivity.getPreferences(MODE_PRIVATE)
        val ed: SharedPreferences.Editor = sharedPreferences.edit()
        ed.putString(SAVED_TEXT, etText.getText().toString())
        ed.commit()
    }
    fun getDate() {
        sharedPreferences = getPreferences(MODE_PRIVATE)
        val savedText: String = sharedPreferences.getString(SAVED_TEXT, "")
    }
}