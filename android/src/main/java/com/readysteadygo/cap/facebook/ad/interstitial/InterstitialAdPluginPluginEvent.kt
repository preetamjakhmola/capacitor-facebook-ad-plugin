package com.readysteadygo.cap.facebook.ad.interstitial

import models.LoadPluginEventNames


object InterstitialAdPluginPluginEvent: LoadPluginEventNames {
    const val Loaded = "interstitialAdLoaded"
    const val FailedToLoad = "interstitialAdFailedToLoad"
    override val Showed = "interstitialAdShowed"
    override val FailedToShow = "interstitialAdFailedToShow"
    override val Dismissed = "interstitialAdDismissed"
}