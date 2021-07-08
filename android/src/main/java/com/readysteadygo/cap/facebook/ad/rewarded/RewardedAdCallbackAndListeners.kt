package com.readysteadygo.cap.facebook.ad.rewarded;

import android.os.Build.VERSION_CODES.N
import androidx.annotation.RequiresApi
import com.getcapacitor.JSObject
import com.getcapacitor.PluginCall
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import models.AdMobPluginError
import com.google.android.gms.common.util.BiConsumer


object RewardedAdCallbackAndListeners {

    fun getOnUserEarnedRewardListener(call: PluginCall, notifyListenersFunction: BiConsumer<String, JSObject>) {
        val response = JSObject()
        response.put("type", "Milestone")
            .put("amount", 1)
        notifyListenersFunction.accept(RewardAdPluginEvents.Rewarded, response)
        call.resolve(response)
    }

    fun failedToShowRewardAdListener(call: PluginCall, notifyListenersFunction: BiConsumer<String, JSObject>) {
        val response = JSObject()
        response.put("type", "Milestone")
            .put("amount", 1)
        notifyListenersFunction.accept(RewardAdPluginEvents.FailedToShow, response)
        call.resolve(response)
    }


    fun getRewardedAdLoadCallback(call: PluginCall, notifyListenersFunction: BiConsumer<String, JSObject>): RewardedAdLoadCallback {
        return object : RewardedAdLoadCallback() {
            @RequiresApi(N)
            override fun onAdLoaded(ad: RewardedAd) {
               /* AdRewardExecutor.mRewardedAd = ad
                AdRewardExecutor.mRewardedAd.fullScreenContentCallback = FullscreenPluginCallback(
                        RewardAdPluginEvents, notifyListenersFunction)
*/

                val adInfo = JSObject()
                adInfo.put("adUnitId", ad.adUnitId)
                call.resolve(adInfo)

                notifyListenersFunction.accept(RewardAdPluginEvents.Loaded, adInfo)
            }

            @RequiresApi(N)
            override fun onAdFailedToLoad(adError: LoadAdError) {
                val adMobError = AdMobPluginError(adError)

                notifyListenersFunction.accept(RewardAdPluginEvents.FailedToLoad, adMobError)
                call.reject(adError.message)
            }
        }
    }

}