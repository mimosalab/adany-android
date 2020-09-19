package com.aravi.adany.facebook;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeAdView;
import com.facebook.ads.NativeBannerAd;
import com.facebook.ads.NativeBannerAdView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class AdanyFacebook {

    public static final String TAG = "ADANY";

    private static LinearLayout linearLayoutHere;
    private static Activity activityHere;
    private static NativeBannerAdView.Type  type;

    private static int AD_TYPE;
    public static NativeBannerAd mNativeBannerAd;
    public static NativeAd mNativeAd;


    public static void initFacebookAds(Context context){
        AudienceNetworkAds.initialize(context);
    }

    public static void loadAutoNativeBanner(Activity activity, String placementId, LinearLayout linearLayout) {
        if (!AudienceNetworkAds.isInitialized(activity.getApplicationContext())){
            Log.e(TAG, "loadAutoNativeBanner: SDK was not initialised, Call - Adany.initFacebookAds(this) ");
            return;
        }
        AD_TYPE = Adany.AUTO_AD_NATIVE_BANNER;
        activityHere = activity;
        linearLayoutHere = linearLayout;

        if (activity  != null){
            mNativeBannerAd = new NativeBannerAd(activity, placementId);
            mNativeBannerAd.setAdListener(nativeAdListener);
            mNativeBannerAd.loadAd();
        }
    }

    public static void loadAutoNativeBanner(Activity activity, String placementId, LinearLayout linearLayout, NativeBannerAdView.Type type) {
        AD_TYPE = Adany.AUTO_AD_NATIVE_BANNER;
        activityHere = activity;
        linearLayoutHere = linearLayout;
        if (activity  != null){
            mNativeBannerAd = new NativeBannerAd(activity, placementId);
            mNativeBannerAd.setAdListener(nativeAdListener);
            mNativeBannerAd.loadAd();
        }
    }

    public static void loadAutoNativeAd(Activity activity, String placementId, LinearLayout linearLayout){
        AD_TYPE = Adany.AUTO_AD_NATIVE;
        mNativeAd = new NativeAd(activity.getApplicationContext(), placementId);
        activityHere = activity;
        linearLayoutHere = linearLayout;
        mNativeAd.loadAd(
                mNativeAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .withMediaCacheFlag(NativeAdBase.MediaCacheFlag.ALL)
                        .build());
    }


    private static NativeAdListener nativeAdListener = new NativeAdListener() {
        @Override
        public void onMediaDownloaded(Ad ad) {
            Log.i(TAG, "onMediaDownloaded: " + ad.getPlacementId());
        }

        @Override
        public void onError(Ad ad, AdError adError) {
            Log.e(TAG, "onError: " + adError.getErrorMessage() );
        }

        @Override
        public void onAdLoaded(Ad ad) {
            if (AD_TYPE == Adany.AUTO_AD_NATIVE_BANNER) {
                type = NativeBannerAdView.Type.HEIGHT_100;
                View adView = NativeBannerAdView.render(activityHere.getApplicationContext(), mNativeBannerAd, type);
                LinearLayout nativeBannerAdContainer = activityHere.findViewById(linearLayoutHere.getId());
                nativeBannerAdContainer.addView(adView);
            } else

                if (AD_TYPE == Adany.AUTO_AD_NATIVE){

                    View adView = NativeAdView.render(activityHere.getApplicationContext(), mNativeAd);
                    LinearLayout nativeAdContainer = (LinearLayout) activityHere.findViewById(linearLayoutHere.getId());
                    nativeAdContainer.addView(adView, new LinearLayout.LayoutParams(MATCH_PARENT, 800));

                }


        }

        @Override
        public void onAdClicked(Ad ad) {

        }

        @Override
        public void onLoggingImpression(Ad ad) {

        }
    };


}
