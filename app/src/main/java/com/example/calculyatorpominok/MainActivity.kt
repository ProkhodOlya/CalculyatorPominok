package com.example.calculyatorpominok

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var textViewDateOfDeath: TextView? = null
    private var textViewDateOfDeathNine: TextView? = null
    private var button: Button? = null
    private var datePicker: MaterialDatePicker<Long>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewDateOfDeath = findViewById(R.id.textDateOfDeath)
        textViewDateOfDeathNine = findViewById(R.id.textDateOfDeathNine)
        button = findViewById(R.id.button)
        datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("В")
            .build()
        datePicker?.addOnPositiveButtonClickListener { selection ->
            val dateOfDeath = getDate(selection, DATE_FORMAT)
            textViewDateOfDeath?.text = dateOfDeath
            val nineDate = getDate(selection + NINE_DATE, DATE_FORMAT)
            textViewDateOfDeathNine?.text = nineDate
            textViewDateOfDeathNine?.visibility = View.VISIBLE
            Log.d ("ninedate = ", nineDate)
        }
        button?.setOnClickListener { datePicker?.show(supportFragmentManager, "tag") }
    }
    companion object {
        private const val DATE_FORMAT = "dd.MM.yyyy"
        private const val THREE_DATE = 1000 * 60 * 60 * 24 * (3-1)
        private const val NINE_DATE = 1000 * 60 * 60 * 24 * (9-1)
        private const val FORTY_DATE = 1000 * 60 * 60 * 24 * (40-1)
    }
}

fun getDate(milliSeconds: Long, dateFormat: String?): String {
    val formatter = SimpleDateFormat(dateFormat)

    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.getTime())
}