package com.calculyatorpominok.data

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.StringRes

class ResourceProvider private constructor (private val context: Context) {
    fun getString(@StringRes id: Int): String = context.getString(id)

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: ResourceProvider? = null
        fun getInstance(context: Context): ResourceProvider {
            if(instance == null) {
                instance = ResourceProvider(context)
            }
            return instance ?: ResourceProvider(context)
        }
    }
}