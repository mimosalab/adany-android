package com.aravi.adany;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.aravi.adany.facebook.Adany;
import com.aravi.adany.facebook.AdanyFacebook;

public class FacebookActivity extends AppCompatActivity {

    LinearLayout adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);

        // This will initialise Facebook Audience Network SDK
        // Make sure you call this on the launch or before loading an ad else it doesn't work or may crash
        AdanyFacebook.initFacebookAds(FacebookActivity.this);

        adView = findViewById(R.id.adViewLinearLayout);

        findViewById(R.id.nativeAd).setOnClickListener(onClickListener);
        findViewById(R.id.nativeBannerAd).setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.nativeAd:
                    AdanyFacebook.loadAutoNativeAd(
                            FacebookActivity.this,
                            "1083416405354600_1180932762269630",
                            adView);
                    break;

                case R.id.nativeBannerAd:
                    AdanyFacebook.loadAutoNativeBanner(
                            FacebookActivity.this,
                            "1083416405354600_1180932762269630",
                            adView);
                    break;
                default:

                    break;
            }
        }
    };
}