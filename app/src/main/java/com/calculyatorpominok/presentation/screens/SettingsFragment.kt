package com.calculyatorpominok.presentation.screens

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.calculyatorpominok.R
import com.calculyatorpominok.mapper.mapToLanguage
import com.calculyatorpominok.presentation.models.TypeOfLanguage
import com.calculyatorpominok.presentation.models.TypeOfTheme
import kotlinx.coroutines.launch


class SettingsFragment : Fragment() {
    private var toolbar: Toolbar? = null
    private var textViewChangeTheme: TextView? = null
    private var radioGroupTheme: RadioGroup? = null
    private var radioButtonLightTheme: RadioButton? = null
    private var radioButtonDarkTheme: RadioButton? = null
    private var radioButtonAutoTheme: RadioButton? = null
    private var radioGroupLanguage: RadioGroup? = null
    private var radioButtonRussian: RadioButton? = null
    private var radioButtonEnglish: RadioButton? = null
    private var radioButtonAutoLanguage: RadioButton? = null
    private val viewModel: SettingsViewModel by viewModels { SettingsViewModel.Factory }

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
        radioGroupLanguage = view.findViewById(R.id.constraintRadioGroupLanguage)
        radioButtonRussian = view.findViewById(R.id.radioButtonRussian)
        radioButtonEnglish = view.findViewById(R.id.radioButtonEnglish)
        radioButtonAutoLanguage = view.findViewById(R.id.radioButtonAutoLanguage)
        toolbar = view.findViewById<Toolbar?>(R.id.toolbar).apply {
            setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
            setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToState()
        viewModel.start()
        initView()
    }

    private fun initView() {
        radioGroupTheme?.setOnCheckedChangeListener(object : OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.radioButtonLightTheme -> {
                        viewModel.saveTheme(TypeOfTheme.LIGHT)
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }

                    R.id.radioButtonDarkTheme -> {
                        viewModel.saveTheme(TypeOfTheme.DARK)
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }

                    R.id.radioButtonAutoTheme -> {
                        viewModel.saveTheme(TypeOfTheme.AUTO)
                        AppCompatDelegate.setDefaultNightMode(
                            if (requireActivity().applicationContext.resources.configuration.uiMode and
                                Configuration.UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
                            ) {
                                AppCompatDelegate.MODE_NIGHT_YES
                            } else {
                                AppCompatDelegate.MODE_NIGHT_NO
                            }
                        )
                    }
                }
            }
        })
        radioGroupLanguage?.setOnCheckedChangeListener(object : OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.radioButtonRussian -> {
                        viewModel.saveLanguage(TypeOfLanguage.RUSSIAN)
                        setLocale(TypeOfLanguage.RUSSIAN)
                    }

                    R.id.radioButtonEnglish -> {
                        viewModel.saveLanguage(TypeOfLanguage.ENGLISH)
                        setLocale(TypeOfLanguage.ENGLISH)
                    }

                    R.id.radioButtonAutoLanguage -> {
                        viewModel.saveLanguage(TypeOfLanguage.AUTO)
                        setLocale(
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                requireActivity().applicationContext.resources.configuration.locales.get(0).language.mapToLanguage()
                            } else {
                                requireActivity().applicationContext.resources.configuration.locale.language.mapToLanguage()
                            }
                        )
                    }
                }
            }
        })
    }

    private fun subscribeToState() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state.typeOfTheme) {
                        TypeOfTheme.DARK -> radioButtonDarkTheme?.isChecked = true
                        TypeOfTheme.LIGHT -> radioButtonLightTheme?.isChecked = true
                        TypeOfTheme.AUTO -> radioButtonAutoTheme?.isChecked = true
                    }
                    when (state.typeOfLanguage) {
                        TypeOfLanguage.AUTO -> radioButtonAutoLanguage?.isChecked = true
                        TypeOfLanguage.ENGLISH -> radioButtonEnglish?.isChecked = true
                        TypeOfLanguage.RUSSIAN -> radioButtonRussian?.isChecked = true
                    }
                }
            }
        }
    }

    private fun setLocale(typeOfLanguage: TypeOfLanguage) {
        val appLocale = LocaleListCompat.forLanguageTags(typeOfLanguage.value) // or use "xx-YY"
        AppCompatDelegate.setApplicationLocales(appLocale)
    }

    companion object {
        const val SETTINGS_FRAGMENT = "settingsFragment"

        fun newInstance() = SettingsFragment()
    }
}
