package com.count.tapcounter

import android.content.Context
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tapcounter.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    var counter = 0

    //for admobBanner ad
    lateinit var mAdView : AdView

    //creating variable for vibrator
    private lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //to load banner Add
        loadBannerAd()

        //Initialize the Vibrator service
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        // Make entire screen clickable - START
//        val rootLayout = findViewById<View>(R.id.rootLayout)
//        rootLayout.setOnClickListener {
//            // Handle click event here
//            // This code will be executed when the screen is clicked
//            if (vibrator.hasVibrator()) {
//                // Vibrate for 500 milliseconds
//                vibrator.vibrate(VibrationEffect.createOneShot(65, VibrationEffect.DEFAULT_AMPLITUDE))
//            }
//            counterButton()
//        }

        //--END

        val countButton: Button = findViewById(R.id.countButton)
        countButton.setOnClickListener {

            // Check if the device supports vibration
            if (vibrator.hasVibrator()) {
                // Vibrate for 500 milliseconds
                vibrator.vibrate(VibrationEffect.createOneShot(35, VibrationEffect.DEFAULT_AMPLITUDE))
            }
            //CountButton
            counterButton()
        }

        val resetButton: Button = findViewById(R.id.resetButton)
        resetButton.setOnClickListener {

            if (vibrator.hasVibrator()) {
                // Vibrate for 500 milliseconds
                vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE))
            }

            //ResetButton
            resetButton()
        }

        val minOneButton : Button = findViewById(R.id.min_one_Button)

        minOneButton.setOnClickListener {
            if (vibrator.hasVibrator()) {
                // Vibrate for 500 milliseconds
                vibrator.vibrate(VibrationEffect.createOneShot(110, VibrationEffect.DEFAULT_AMPLITUDE))
            }

            minusOne()
        }
    }


    private fun loadBannerAd() {
        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mAdView.adListener = object: AdListener() {
            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        }
    }


    public fun counterButton() {
        counter++

        var resultTextview : TextView = findViewById(R.id.countText)
        resultTextview.text = counter.toString()
    }

    private fun resetButton() {
        counter = 0

        val resultTextview : TextView = findViewById(R.id.countText)
        resultTextview.text = counter.toString()
    }

    private fun minusOne() {
        counter = counter - 1

        val resultTextView: TextView = findViewById(R.id.countText)
        resultTextView.text = counter.toString()
    }
}
