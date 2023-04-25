package com.example.calculyatorpominok

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.calculyatorpominok.R.menu.top_app_bar
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import com.example.calculyatorpominok.utils.ARGS_DAY_OF_COMMEMORATION
import com.example.calculyatorpominok.utils.DayOfCommemoration
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import java.util.Calendar


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
    private var topAppBar: MaterialToolbar? = null
    private var constraintDetailsThreeDay: ConstraintLayout? = null
    private var constraintDetailsNineDay: ConstraintLayout? = null
    private var constraintDetailsFortyDay: ConstraintLayout? = null
    private var constraintDetailsSixMonth: ConstraintLayout? = null
    private var constraintDetailsOneYear: ConstraintLayout? = null


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
        constraintDetailsThreeDay = findViewById(R.id.constraintDateOfDeathThree)
        constraintDetailsNineDay = findViewById(R.id.constraintDateOfDeathNine)
        constraintDetailsFortyDay = findViewById(R.id.constraintDateOfDeathForty)
        constraintDetailsSixMonth = findViewById(R.id.constraintDateOfDeathSixMonths)
        constraintDetailsOneYear = findViewById(R.id.constraintDateOfDeathOneYear)
        button = findViewById(R.id.button)
        topAppBar = findViewById(R.id.topAppBar)
        setSupportActionBar(topAppBar)
        val calendarConstraints = CalendarConstraints
            .Builder()
            .setValidator(
                DateValidatorPointBackward.now()
            ).build()
        datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(R.string.choose_date)
            .setCalendarConstraints(calendarConstraints)
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
        constraintDetailsThreeDay?.setOnClickListener {
            openDetails(DayOfCommemoration.THREE_DAY)
        }
        constraintDetailsNineDay?.setOnClickListener {
            openDetails(DayOfCommemoration.NINE_DAY)
        }
        constraintDetailsFortyDay?.setOnClickListener {
            openDetails(DayOfCommemoration.FORTY_DAY)
        }
        constraintDetailsSixMonth?.setOnClickListener {
            openDetails(DayOfCommemoration.SIX_MONTH)
        }
        constraintDetailsOneYear?.setOnClickListener {
            openDetails(DayOfCommemoration.ONE_YEAR)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.calendar) {
            datePicker?.show(supportFragmentManager, "tag")
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(top_app_bar, menu);
        return super.onCreateOptionsMenu(menu)
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

    private fun openDetails(dayOfCommemoration: DayOfCommemoration) {
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

