package com.example.calculyatorpominok

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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
        setContentView(R.layout.activity_main2)
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
            setAllDates(selection)
        }
        button?.setOnClickListener { datePicker?.show(supportFragmentManager, "tag") }

        val time = System.currentTimeMillis()
        setAllDates(time)

    }

    private fun setDate(selection: Long, amount: Int, calendarField: Int, textView: TextView?) {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = selection
        calendar.add(calendarField, amount)
        val timeInMillis = calendar.timeInMillis
        val dateString = getDate(timeInMillis, DATE_FORMAT)
        textView?.text = dateString
        textView?.isVisible = true
    }

    private fun setAllDates(selection: Long) {
        val dateOfDeath = getDate(selection, DATE_FORMAT_DEATH)
        textViewDateOfDeath?.text = dateOfDeath

        setDate(selection, THREE_DATE, Calendar.DAY_OF_MONTH, textViewDateOfDeathThree)
        setDate(selection, NINE_DATE, Calendar.DAY_OF_MONTH, textViewDateOfDeathNine)
        setDate(selection, FORTY_DATE, Calendar.DAY_OF_MONTH, textViewDateOfDeathForty)
        setDate(selection, SIXMONTH_DATE, Calendar.MONTH, textViewDateOfDeathSixMonth)
        setDate(selection, ONEYEAR_DATE, Calendar.YEAR, textViewDateOfDeathOneYear)
    }

    companion object {
        private const val DATE_FORMAT_DEATH = "dd MMMM yyyy"
        private const val DATE_FORMAT = "dd.MM.yyyy, EEEE"
        private const val THREE_DATE = 3 - 1
        private const val NINE_DATE = 9 - 1
        private const val FORTY_DATE = 40 - 1
        private const val SIXMONTH_DATE = 6
        private const val ONEYEAR_DATE = 1

    }
}

fun getDate(milliSeconds: Long, dateFormat: String?): String {
    val formatter = SimpleDateFormat(dateFormat)

    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.time)
}