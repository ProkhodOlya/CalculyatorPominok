package com.calculyatorpominok.presentation.main

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.calculyatorpominok.R
import com.calculyatorpominok.di.AppComponent
import com.calculyatorpominok.mapper.mapToLanguage
import com.calculyatorpominok.presentation.details.DetailsFragment
import com.calculyatorpominok.presentation.details.DetailsFragment.Companion.DETAILS_FRAGMENT
import com.calculyatorpominok.presentation.models.TypeOfLanguage
import com.calculyatorpominok.presentation.models.TypeOfTheme
import com.calculyatorpominok.presentation.screens.SettingsFragment
import com.calculyatorpominok.presentation.screens.SettingsFragment.Companion.SETTINGS_FRAGMENT
import com.calculyatorpominok.utils.DayOfCommemoration
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
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
    private var constraintDateOfDeath: ConstraintLayout? = null
    private var constraintDetailsThreeDay: ConstraintLayout? = null
    private var constraintDetailsNineDay: ConstraintLayout? = null
    private var constraintDetailsFortyDay: ConstraintLayout? = null
    private var constraintDetailsSixMonth: ConstraintLayout? = null
    private var constraintDetailsOneYear: ConstraintLayout? = null
    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val appComponent: AppComponent = DaggerAppCom
//    }
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
        constraintDateOfDeath = view.findViewById(R.id.constraintDateOfDeath)
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
        constraintDateOfDeath?.setOnClickListener { datePicker?.show(parentFragmentManager, "tag") }

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

                R.id.settings -> {
                    // Navigate to settings screen.
                    openSettings()
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
                    textViewDateOfDeath?.text = getDate(mainState.dateOfDeath, DATE_FORMAT_DEATH)
                    textViewDateOfDeathThree?.text =
                        getDate(mainState.threeDateOfDeath, DATE_FORMAT)
                    textViewDateOfDeathNine?.text = getDate(mainState.nineDateOfDeath, DATE_FORMAT)
                    textViewDateOfDeathForty?.text =
                        getDate(mainState.fortyDateOfDeath, DATE_FORMAT)
                    textViewDateOfDeathSixMonth?.text =
                        getDate(mainState.sixMonthDateOfDeath, DATE_FORMAT)
                    textViewDateOfDeathOneYear?.text =
                        getDate(mainState.oneYearDateOfDeath, DATE_FORMAT)
                    when (mainState.typeOfTheme) {
                        TypeOfTheme.AUTO -> AppCompatDelegate.setDefaultNightMode(
                            if (resources.configuration.uiMode and
                                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
                            ) {
                                AppCompatDelegate.MODE_NIGHT_YES
                            } else {
                                AppCompatDelegate.MODE_NIGHT_NO
                            }
                        )

                        TypeOfTheme.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        TypeOfTheme.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                    when (mainState.typeOfLanguage) {
                        TypeOfLanguage.AUTO -> (
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                requireActivity().applicationContext.resources.configuration.locales.get(
                                    0
                                ).language.mapToLanguage()
                            } else {
                                @Suppress("DEPRECATION")
                                requireActivity().applicationContext.resources.configuration.locale.language.mapToLanguage()
                            }
                            )
                        TypeOfLanguage.RUSSIAN -> setLocale(TypeOfLanguage.RUSSIAN)
                        TypeOfLanguage.ENGLISH -> setLocale(TypeOfLanguage.ENGLISH)
                    }
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

    private fun openSettings() {
        val fragmentTransaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        val settingsFragment = SettingsFragment.newInstance()
        fragmentTransaction.add(R.id.containerFragment, settingsFragment)
            .addToBackStack(SETTINGS_FRAGMENT)
            .setCustomAnimations(
                android.R.animator.fade_in, android.R.animator.fade_out
            )
            .commit()
    }

    private fun getDate(milliSeconds: Long, dateFormat: String?): String {
        //TODO лучше не использовать Locale.getDefault() - медленно работает
        val formatter = SimpleDateFormat(dateFormat, getCurrentLocale(requireContext()))
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }

    private fun getCurrentLocale(context: Context): Locale? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales[0]
        } else {
            @Suppress("DEPRECATION")
            context.resources.configuration.locale
        }
    }

    private fun setLocale(typeOfLanguage: TypeOfLanguage) {
        val appLocale = LocaleListCompat.forLanguageTags(typeOfLanguage.value) // or use "xx-YY"
        AppCompatDelegate.setApplicationLocales(appLocale)
    }

    companion object {
        const val MAIN_FRAGMENT = "mainFragment"
        private const val DATE_FORMAT_DEATH = "dd MMMM yyyy"
        private const val DATE_FORMAT = "dd.MM.yyyy, EEEE"

        fun newInstance() = MainFragment()
    }
}