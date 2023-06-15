package com.calculyatorpominok.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.calculyatorpominok.presentation.details.DetailsFragment
import com.calculyatorpominok.presentation.details.DetailsFragment.Companion.DETAILS_FRAGMENT
import com.calculyatorpominok.utils.DayOfCommemoration
import com.example.calculyatorpominok.R
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch

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
    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }

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
        subscribeToState()
        viewModel.start()
        initView()
    }

    private fun initView() {
        initToolbar()
        initDatePicker()
        button?.setOnClickListener { datePicker?.show(parentFragmentManager, "tag") }

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

    private fun initDatePicker() {
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
            viewModel.onSelectDate(selection)
        }
    }

    private fun initToolbar() {
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
    }

    private fun subscribeToState() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { mainState ->
                    textViewDateOfDeath?.text = mainState.dateOfDeath
                    textViewDateOfDeathThree?.text = mainState.threeDateOfDeath
                    textViewDateOfDeathNine?.text = mainState.nineDateOfDeath
                    textViewDateOfDeathForty?.text = mainState.fortyDateOfDeath
                    textViewDateOfDeathSixMonth?.text = mainState.sixMonthDateOfDeath
                    textViewDateOfDeathOneYear?.text = mainState.oneYearDateOfDeath
                }
            }
        }
    }

    private fun openDetails(dayOfCommemoration: DayOfCommemoration) {
        val fragmentTransaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        val detailsFragment = DetailsFragment.newInstance(dayOfCommemoration.value)
        fragmentTransaction.add(R.id.containerFragment, detailsFragment, DETAILS_FRAGMENT)
            .addToBackStack(DETAILS_FRAGMENT)
            .setCustomAnimations(
                android.R.animator.fade_in, android.R.animator.fade_out
            )
            .commit()
    }

    companion object {
        const val MAIN_FRAGMENT = "mainFragment"

        fun newInstance() = MainFragment()
    }
}