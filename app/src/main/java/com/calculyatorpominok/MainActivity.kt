package com.calculyatorpominok

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.calculyatorpominok.presentation.details.DetailsFragment
import com.calculyatorpominok.presentation.main.MainFragment
import com.calculyatorpominok.presentation.main.MainFragment.Companion.MAIN_FRAGMENT
import com.calculyatorpominok.utils.ARGS_DAY_OF_COMMEMORATION
import com.example.calculyatorpominok.R
import com.example.calculyatorpominok.R.menu.toolbar_main_fragment
import com.google.android.material.datepicker.MaterialDatePicker


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.containerFragment, MainFragment.newInstance(), MAIN_FRAGMENT)
            .addToBackStack(null)
            .setCustomAnimations(
                android.R.animator.fade_in, android.R.animator.fade_out
            )
            .commit()
    }
}

