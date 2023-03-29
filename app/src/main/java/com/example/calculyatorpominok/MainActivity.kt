package com.example.calculyatorpominok

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*
import com.example.calculyatorpominok.utils.ARGS_DAY_OF_COMMEMORATION
import com.example.calculyatorpominok.utils.DayOfCommemoration


class MainActivity : AppCompatActivity() {
    private var textViewDateOfDeath: TextView? = null
    private var textViewDateOfDeathThree: TextView? = null
    private var textViewDateOfDeathThreeDetails: TextView? = null
    private var textViewDateOfDeathNine: TextView? = null
    private var textViewDateOfDeathNineDetails: TextView? = null
    private var textViewDateOfDeathForty: TextView? = null
    private var textViewDateOfDeathFortyDetails: TextView? = null
    private var textViewDateOfDeathSixMonth: TextView? = null
    private var textViewDateOfDeathSixMonthDetails: TextView? = null
    private var textViewDateOfDeathOneYear: TextView? = null
    private var textViewDateOfDeathOneYearDetails: TextView? = null
    private var button: Button? = null
    private var datePicker: MaterialDatePicker<Long>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewDateOfDeath = findViewById(R.id.textDateOfDeath)
        textViewDateOfDeathThree = findViewById(R.id.textDateOfDeathThree)
        textViewDateOfDeathThreeDetails = findViewById(R.id.textDateOfDeathThreeDetails)
        textViewDateOfDeathNine = findViewById(R.id.textDateOfDeathNine)
        textViewDateOfDeathNineDetails = findViewById(R.id.textDateOfDeathNineDetails)
        textViewDateOfDeathForty = findViewById(R.id.textDateOfDeathForty)
        textViewDateOfDeathFortyDetails = findViewById(R.id.textDateOfDeathFortyDetails)
        textViewDateOfDeathSixMonth = findViewById(R.id.textDateOfDeathSixMonths)
        textViewDateOfDeathSixMonthDetails = findViewById(R.id.textDateOfDeathSixMonthsDetails)
        textViewDateOfDeathOneYear = findViewById(R.id.textDateOfDeathOneYear)
        textViewDateOfDeathOneYearDetails = findViewById(R.id.textDateOfDeathOneYearDetails)
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

        textViewDateOfDeathThreeDetails?.setOnClickListener {
            openDetails(DayOfCommemoration.THREE_DAY)
        }

        textViewDateOfDeathNineDetails?.setOnClickListener {
            openDetails(DayOfCommemoration.NINE_DAY)
        }

        textViewDateOfDeathFortyDetails?.setOnClickListener {
            openDetails(DayOfCommemoration.FORTY_DAY)
        }

        textViewDateOfDeathSixMonthDetails?.setOnClickListener {
            openDetails(DayOfCommemoration.SIX_MONTH)
        }

        textViewDateOfDeathOneYearDetails?.setOnClickListener {
            openDetails(DayOfCommemoration.ONE_YEAR)
        }
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

    private fun openDetails (dayOfCommemoration: DayOfCommemoration) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(ARGS_DAY_OF_COMMEMORATION, dayOfCommemoration.value)
        startActivity(intent)
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

