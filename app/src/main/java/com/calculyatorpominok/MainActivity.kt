package com.calculyatorpominok

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.calculyatorpominok.presentation.main.FragmentForFragment
import com.calculyatorpominok.presentation.main.FragmentForFragment.Companion.FRAGMENT_FOR_FRAGMENT

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.containerFragment, FragmentForFragment.newInstance(), FRAGMENT_FOR_FRAGMENT)
            .addToBackStack(null)
            .setCustomAnimations(
                android.R.animator.fade_in, android.R.animator.fade_out
            )
            .commit()
//        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.containerFragment, MainFragment.newInstance(), MAIN_FRAGMENT)
//            .addToBackStack(null)
//            .setCustomAnimations(
//                android.R.animator.fade_in, android.R.animator.fade_out
//            )
//            .commit()
    }
}

