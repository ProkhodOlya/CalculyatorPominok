package com.calculyatorpominok.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.calculyatorpominok.details.DetailsActivity
import com.calculyatorpominok.utils.ARGS_DAY_OF_COMMEMORATION
import com.calculyatorpominok.utils.DayOfCommemoration
import com.example.calculyatorpominok.R
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar

class MainFragment : Fragment() {
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
    private var toolbar: Toolbar? = null
    private var constraintDetailsThreeDay: ConstraintLayout? = null
    private var constraintDetailsNineDay: ConstraintLayout? = null
    private var constraintDetailsFortyDay: ConstraintLayout? = null
    private var constraintDetailsSixMonth: ConstraintLayout? = null
    private var constraintDetailsOneYear: ConstraintLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        textViewDateOfDeath = view.findViewById(R.id.textDateOfDeath)
        textViewDateOfDeathThree = view.findViewById(R.id.textDateOfDeathThree)
        textViewDateOfDeathThreeDetails = view.findViewById(R.id.textDateOfDeathThreeDetails)
        textViewDateOfDeathNine = view.findViewById(R.id.textDateOfDeathNine)
        textViewDateOfDeathNineDetails = view.findViewById(R.id.textDateOfDeathNineDetails)
        textViewDateOfDeathForty = view.findViewById(R.id.textDateOfDeathForty)
        textViewDateOfDeathFortyDetails = view.findViewById(R.id.textDateOfDeathFortyDetails)
        textViewDateOfDeathSixMonth = view.findViewById(R.id.textDateOfDeathSixMonths)
        textViewDateOfDeathSixMonthDetails = view.findViewById(R.id.textDateOfDeathSixMonthsDetails)
        textViewDateOfDeathOneYear = view.findViewById(R.id.textDateOfDeathOneYear)
        textViewDateOfDeathOneYearDetails = view.findViewById(R.id.textDateOfDeathOneYearDetails)
        constraintDetailsThreeDay = view.findViewById(R.id.constraintDateOfDeathThree)
        constraintDetailsNineDay = view.findViewById(R.id.constraintDateOfDeathNine)
        constraintDetailsFortyDay = view.findViewById(R.id.constraintDateOfDeathForty)
        constraintDetailsSixMonth = view.findViewById(R.id.constraintDateOfDeathSixMonths)
        constraintDetailsOneYear = view.findViewById(R.id.constraintDateOfDeathOneYear)
        button = view.findViewById(R.id.button)
        toolbar = view.findViewById(R.id.toolbar)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.calendar -> {
                    // Navigate to settings screen.
                    datePicker?.show(parentFragmentManager, "datePicker")
                    true
                }
                else -> false
            }
        }

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
        button?.setOnClickListener { datePicker?.show(parentFragmentManager, "tag") }

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
        val intent = Intent(requireContext(), DetailsActivity::class.java)
        intent.putExtra(ARGS_DAY_OF_COMMEMORATION, dayOfCommemoration.value)
        startActivity(intent)
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

    companion object {
        private const val DATE_FORMAT_DEATH = "dd MMMM yyyy"
        private const val DATE_FORMAT = "dd.MM.yyyy, EEEE"
        private const val THREE_DATE = 3 - 1
        private const val NINE_DATE = 9 - 1
        private const val FORTY_DATE = 40 - 1
        private const val SIXMONTH_DATE = 6
        private const val ONEYEAR_DATE = 1
    }

    fun getDate(milliSeconds: Long, dateFormat: String?): String {
        val formatter = SimpleDateFormat(dateFormat)
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }
}