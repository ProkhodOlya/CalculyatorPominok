package com.calculyatorpominok.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.calculyatorpominok.BuildConfig
import com.calculyatorpominok.R
import com.calculyatorpominok.presentation.main.MainFragment.Companion.MAIN_FRAGMENT
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.banner.BannerAdSize
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import kotlin.math.roundToInt


class FragmentForFragment : Fragment() {
    private var containerFragmentForFragment: FrameLayout? = null
    private var bannerAd: BannerAdView? = null
    private var viewClose: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_container, container, false)
        containerFragmentForFragment = view.findViewById(R.id.containerFragmentForFragment)
        bannerAd = view.findViewById(R.id.ad_container_view)
        viewClose = view.findViewById(R.id.ad_view_close)

        initView()
        return view
    }

    private fun initView() {
        openMainFragment()
        bannerAd?.viewTreeObserver?.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                bannerAd?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
                adSize?.let {
                    loadBannerAd(it)
                }
            }
        })
        viewClose?.setOnClickListener {
            viewClose?.visibility = View.GONE
            bannerAd?.visibility = View.GONE
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

    private val adSize: BannerAdSize?
        get() =
            context?.let { ctx ->
                // Calculate the width of the ad, taking into account the padding in the ad container.
                var adWidthPixels = bannerAd?.width ?: 0
                if (adWidthPixels == 0) {
                    // If the ad hasn't been laid out, default to the full screen width
                    adWidthPixels = ctx.resources.displayMetrics.widthPixels
                }
                val adWidth = (adWidthPixels / ctx.resources.displayMetrics.density).roundToInt()

                BannerAdSize.stickySize(ctx, adWidth)
            }

    private fun loadBannerAd(adSize: BannerAdSize): BannerAdView? {
        return bannerAd?.apply {
            setAdSize(adSize)
            // TODO - Проверить, отрабатывает ли реальный блок ID из партнерского интерфейса, было "demo-banner-mytarget"
            setAdUnitId(if (BuildConfig.DEBUG) "demo-banner-yandex" else "R-M-5859502-1")
            setBannerAdEventListener(object : BannerAdEventListener {
                override fun onAdLoaded() {
                    // If this callback occurs after the activity is destroyed, you
                    // must call destroy and return or you may get a memory leak.
                    // Note `isDestroyed` is a method on Activity.
                    if (this@FragmentForFragment.isDetached || this@FragmentForFragment.isRemoving) {
                        bannerAd?.destroy()
                        return
                    }
                    println(">>>YandexADS onAdLoaded")
                    viewClose?.visibility = View.VISIBLE
                }

                override fun onAdFailedToLoad(adRequestError: AdRequestError) {
                    // Ad failed to load with AdRequestError.
                    // Attempting to load a new ad from the onAdFailedToLoad() method is strongly discouraged.
                    println(">>>YandexADS onAdFailedToLoad")
                }

                override fun onAdClicked() {
                    // Called when a click is recorded for an ad.
                    println(">>>YandexADS onAdClicked")
                }

                override fun onLeftApplication() {
                    // Called when user is about to leave application (e.g., to go to the browser), as a result of clicking on the ad.
                    println(">>>YandexADS onLeftApplication")
                }

                override fun onReturnedToApplication() {
                    // Called when user returned to application after click.
                    println(">>>YandexADS onReturnedToApplication")
                }

                override fun onImpression(impressionData: ImpressionData?) {
                    // Called when an impression is recorded for an ad.
                    if (impressionData != null) {
                        println(">>>YandexADS onImpression" + impressionData.rawData)
                    }
                }
            })
            loadAd(
                AdRequest.Builder()
                    // Methods in the AdRequest.Builder class can be used here to specify individual options settings.
                    .build()
            )
        }
    }

    companion object {
        const val FRAGMENT_FOR_FRAGMENT = "fragmentForFragment"
        fun newInstance() = FragmentForFragment()
    }
}