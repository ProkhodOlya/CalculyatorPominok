package com.calculyatorpominok.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.calculyatorpominok.R
import com.calculyatorpominok.presentation.main.MainFragment.Companion.MAIN_FRAGMENT
import com.yandex.mobile.ads.banner.BannerAdView


class FragmentForFragment : Fragment() {
    private var containerFragmentForFragment: FrameLayout? = null
    private var textViewJust1: TextView? = null
    private var ad_container_view: BannerAdView? = null
    private var textAds: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_container, container, false)
        containerFragmentForFragment = view.findViewById(R.id.containerFragmentForFragment)
        textViewJust1 = view.findViewById(R.id.textViewJust1)
//        ad_container_view = view.findViewById(R.id.ad_container_view)
        textAds = view.findViewById(R.id.textAds)
        initView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {
        openMainFragment()
        textAds?.setOnClickListener {
//            TODO open ads_yandex
        }

    }

    private fun openMainFragment() {
        val fragmentTransaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.containerFragmentForFragment,
            MainFragment.newInstance(),
            MAIN_FRAGMENT
        )
            .addToBackStack(null)
            .setCustomAnimations(
                android.R.animator.fade_in, android.R.animator.fade_out
            )
            .commit()
    }

    companion object {
        const val FRAGMENT_FOR_FRAGMENT = "fragmentForFragment"
        fun newInstance() = FragmentForFragment()
    }
}