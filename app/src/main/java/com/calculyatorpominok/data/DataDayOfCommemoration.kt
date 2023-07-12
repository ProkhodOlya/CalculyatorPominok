package com.calculyatorpominok.data

import com.google.gson.annotations.SerializedName

data class DataDayOfCommemoration(
    @SerializedName("title")  val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("actionsList") val actionsList: List<String>? = null,
)
