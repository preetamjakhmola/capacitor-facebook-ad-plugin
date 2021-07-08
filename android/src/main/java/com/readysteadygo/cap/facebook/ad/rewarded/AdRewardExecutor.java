package com.readysteadygo.cap.facebook.ad.rewarded;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.util.Supplier;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.google.android.gms.common.util.BiConsumer;

import models.Executor;

public class AdRewardExecutor extends Executor {

    private InterstitialAd interstitialAd;
    private final String TAG = AdRewardExecutor.class.getSimpleName();
    InterstitialAdListener interstitialAdListener;
    public AdRewardExecutor(
        Supplier<Context> contextSupplier,
        Supplier<Activity> activitySupplier,
        BiConsumer<String, JSObject> notifyListenersFunction,
        String pluginLogTag
    ) {
        super(contextSupplier, activitySupplier, notifyListenersFunction, pluginLogTag, "AdRewardExecutor");
    }

    @PluginMethod
    public void prepareRewardVideoAd(final PluginCall call, BiConsumer<String, JSObject> notifyListenersFunction) {
        //final AdOptions adOptions = AdOptions.getFactory().createRewardVideoOptions(call);

        activitySupplier
            .get()
            .runOnUiThread(
                () -> {
                    try {
                        //final AdRequest adRequest = RequestHelper.createRequest(adOptions);
                        final String placementId =call.getString("adId"); //AdViewIdHelper.getFinalAdId(adOptions, adRequest, logTag, contextSupplier.get());
                        AudienceNetworkAds.initialize(activitySupplier.get());
                      /*  RewardedAd.load(
                            contextSupplier.get(),
                            id,
                            adRequest,
                            RewardedAdCallbackAndListeners.INSTANCE.getRewardedAdLoadCallback(call, notifyListenersFunction)
                        );*/
                        interstitialAd = new  InterstitialAd(activitySupplier.get(), placementId);
                         interstitialAdListener = new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {
                                // Interstitial ad displayed callback
                                Log.e(TAG, "Interstitial ad displayed.");
                            }

                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                // Interstitial dismissed callback
                                RewardedAdCallbackAndListeners.INSTANCE.getOnUserEarnedRewardListener(call, notifyListenersFunction);
                                Log.e(TAG, "Interstitial ad dismissed.");
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {
                                // Ad error callback

                                JSObject jsObject = new JSObject();
                                jsObject.put("ErrorCode", adError.getErrorCode())
                                        .put("ErrorMessage", adError.getErrorMessage());
                                notifyListenersFunction.accept("onRewardedVideoAdFailedToShow", jsObject);

                                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
                                call.resolve(jsObject);
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                // Interstitial ad is loaded and ready to be displayed
                                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                                if(interstitialAd != null && interstitialAd.isAdLoaded())
                                    interstitialAd.show();
                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                                // Ad clicked callback
                                Log.d(TAG, "Interstitial ad clicked!");
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                                // Ad impression logged callback
                                Log.d(TAG, "Interstitial ad impression logged!");
                            }
                        };
                         JSObject jsObject = new JSObject();
                         jsObject.put("Ad Prepared","Add is ready to show now.");
                        call.resolve(jsObject);
                    } catch (Exception ex) {
                        call.reject(ex.getLocalizedMessage(), ex);
                    }
                }
            );
    }

    @PluginMethod
    public void showRewardVideoAd(final PluginCall call, BiConsumer<String, JSObject> notifyListenersFunction) {
       /* if (mRewardedAd == null) {
            String errorMessage = "No Reward Video Ad can be show. It was not prepared or maybe it failed to be prepared.";
            call.reject(errorMessage);
            AdMobPluginError errorObject = new AdMobPluginError(-1, errorMessage);
            notifyListenersFunction.accept(RewardAdPluginEvents.FailedToLoad, errorObject);
            return;
        }*/

        try {
            activitySupplier
                .get()
                .runOnUiThread(
                    () -> {

                        interstitialAd.loadAd(
                                interstitialAd.buildLoadAdConfig()
                                        .withAdListener(interstitialAdListener)
                                        .build());

                        /*mRewardedAd.show(
                            activitySupplier.get(),
                            RewardedAdCallbackAndListeners.INSTANCE.getOnUserEarnedRewardListener(call, notifyListenersFunction)
                        );*/
                        JSObject jsObject = new JSObject();
                        jsObject.put("Ad showed","Add is showed.");
                        call.resolve(jsObject);
                    }
                );
        } catch (Exception ex) {
            call.reject(ex.getLocalizedMessage(), ex);
        }
    }
}
