package com.calculyatorpominok.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.calculyatorpominok.presentation.main.MainFragment
import com.example.calculyatorpominok.R



class SettingsFragment : Fragment() {
    private var toolbar: Toolbar? = null
    private var textViewChangeTheme: TextView? = null
    private var radioGroupTheme: RadioGroup? = null
    private var radioButtonLightTheme: RadioButton? = null
    private var radioButtonDarkTheme: RadioButton? = null
    private var radioButtonAutoTheme: RadioButton? = null
//    private val viewModel: SettingsViewModel by viewModels { SettingsViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        textViewChangeTheme = view.findViewById(R.id.textViewTheme)
        radioGroupTheme = view.findViewById(R.id.constraintRadioGroup)
        radioButtonLightTheme = view.findViewById(R.id.radioButtonLightTheme)
        radioButtonDarkTheme = view.findViewById(R.id.radioButtonDarkTheme)
        radioButtonAutoTheme = view.findViewById(R.id.radioButtonAutoTheme)
        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//            subscribeToState()
//        viewModel.start()
//        initView()}


//    private fun initView() {
//        initToolbar()
//        radioGroupTheme.setOnCheckedChangeListener(RadioGroup?.OnCheckedChangeListener() {
//            @Override
////            fun onCheckedChanged(RadioGroup? group, int checkedId) {
////                when (checkedId) {
////                    R.id.radioButtonLightTheme -> {
////                        //some code
////                    }
////                    R.id.radioButtonDarkTheme -> {
////                        //some code
////                    }
////                    R.id.radioButtonAutoTheme -> {
////                        //some code
////                    }
////                }
////            }
//        })
//    }
//    private fun subscribeToState() {
//        lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.state.collect { mainState ->
//                    textViewDateOfDeath?.text = mainState.dateOfDeath
//                    textViewDateOfDeathThree?.text = mainState.threeDateOfDeath
//                    textViewDateOfDeathNine?.text = mainState.nineDateOfDeath
//                    textViewDateOfDeathForty?.text = mainState.fortyDateOfDeath
//                    textViewDateOfDeathSixMonth?.text = mainState.sixMonthDateOfDeath
//                    textViewDateOfDeathOneYear?.text = mainState.oneYearDateOfDeath
//                }
//            }
//        }
companion object {
    const val SETTINGS_FRAGMENT = "settingsFragment"

    fun newInstance() = SettingsFragment()
}
}
