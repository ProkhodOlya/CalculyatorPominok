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
    private var textViewDateOfDeathThree: TextView? = null
    private var textViewDateOfDeathNine: TextView? = null
    private var textViewDateOfDeathForty: TextView? = null
    private var textViewDateOfDeathSixMonth: TextView? = null
    private var textViewDateOfDeathOneYear: TextView? = null
    private var button: Button? = null
    private var datePicker: MaterialDatePicker<Long>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewDateOfDeath = findViewById(R.id.textDateOfDeath)
        textViewDateOfDeathThree = findViewById(R.id.textDateOfDeathThree)
        textViewDateOfDeathNine = findViewById(R.id.textDateOfDeathNine)
        textViewDateOfDeathForty = findViewById(R.id.textDateOfDeathForty)
        textViewDateOfDeathSixMonth = findViewById(R.id.textDateOfDeathSixMonths)
        textViewDateOfDeathOneYear = findViewById(R.id.textDateOfDeathOneYear)
        button = findViewById(R.id.button)
        datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("В")
            .build()
        datePicker?.addOnPositiveButtonClickListener { selection ->
            val dateOfDeath = getDate(selection, DATE_FORMAT)
            textViewDateOfDeath?.text = dateOfDeath
            val threeDay = getDate(selection + THREE_DATE, DATE_FORMAT)
            textViewDateOfDeathThree?.text = threeDay
            textViewDateOfDeathThree?.visibility = View.VISIBLE
            val nineDate = getDate(selection + NINE_DATE, DATE_FORMAT)
            textViewDateOfDeathNine?.text = nineDate
            textViewDateOfDeathNine?.visibility = View.VISIBLE
            Log.d ("ninedate = ", nineDate)
            val fortyDate = getDate(selection + FORTY_DATE, DATE_FORMAT)
            textViewDateOfDeathForty?.text = fortyDate
            textViewDateOfDeathForty?.visibility = View.VISIBLE
            val sixMonthDate = getDate(selection + SIXMONTH_DATE, DATE_FORMAT)
            textViewDateOfDeathSixMonth?.text = sixMonthDate
            textViewDateOfDeathSixMonth?.visibility = View.VISIBLE
            val oneYearDate = getDate(selection + ONEYEAR_DATE, DATE_FORMAT)
            textViewDateOfDeathOneYear?.text = oneYearDate
            textViewDateOfDeathOneYear?.visibility = View.VISIBLE
        }
        button?.setOnClickListener { datePicker?.show(supportFragmentManager, "tag") }
    }
    companion object {
        private const val DATE_FORMAT = "dd.MM.yyyy"
        private const val THREE_DATE = 1000 * 60 * 60 * 24 * (3-1)
        private const val NINE_DATE = 1000 * 60 * 60 * 24 * (9-1)
        private const val FORTY_DATE: Long = 1000L * 60L * 60L * 24L * (40L-1L)
        private const val SIXMONTH_DATE: Long = 1000L * 60L * 60L * 24L * (180L-1L)
        private const val ONEYEAR_DATE: Long = 1000L * 60L * 60L * 24L * (365L)

    }
}

fun getDate(milliSeconds: Long, dateFormat: String?): String {
    val formatter = SimpleDateFormat(dateFormat)

    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.time)
}