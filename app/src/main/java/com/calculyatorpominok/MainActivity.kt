package com.calculyatorpominok

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.calculyatorpominok.presentation.main.MainFragment
import com.calculyatorpominok.presentation.main.MainFragment.Companion.MAIN_FRAGMENT
import com.example.calculyatorpominok.R


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

