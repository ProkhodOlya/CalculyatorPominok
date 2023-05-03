package com.calculyatorpominok

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.calculyatorpominok.main.MainFragment
import com.example.calculyatorpominok.R
import com.example.calculyatorpominok.R.menu.toolbar_main_fragment
import com.google.android.material.datepicker.MaterialDatePicker


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val mainFragment = MainFragment()
        fragmentTransaction.replace(R.id.containerFragment, mainFragment, "mainFragment")
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.setCustomAnimations(
            android.R.animator.fade_in, android.R.animator.fade_out
        )
        fragmentTransaction.commit()
    }
}

