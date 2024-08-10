package com.example.ttmaker.adsContainer

import android.app.Activity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

fun InterstitialAdContainerPDF(activity: Activity){
//    AndroidView(modifier = modifier, factory = {
        InterstitialAd.load(activity,
            //real
//            "ca-app-pub-6805995445952780/3750876158",

            //fake
            "ca-app-pub-3940256099942544/1033173712",
                AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback(){
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    super.onAdLoaded(interstitialAd)
                    interstitialAd.show(activity)

                }
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                }
            })

//    })
}
fun InterstitialAdContainerEXCEL(activity: Activity){
//    AndroidView(modifier = modifier, factory = {
    InterstitialAd.load(activity,
        //real
//"ca-app-pub-6805995445952780/7956411136",
        //fake
        "ca-app-pub-3940256099942544/1033173712",
        AdRequest.Builder().build(),
        object : InterstitialAdLoadCallback(){
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                super.onAdLoaded(interstitialAd)
                interstitialAd.show(activity)

            }
            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
            }
        })

//    })
}