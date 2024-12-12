package com.codesui.footballlatest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.codesui.footballlatest.MainActivity;
import com.codesui.footballlatest.R;
import com.codesui.footballlatest.ads.AppOpenManager;
import com.codesui.footballlatest.ads.BannerManager;
import com.codesui.footballlatest.ads.InterstitialManager;
import com.google.android.material.button.MaterialButton;

public class StartActivity extends AppCompatActivity {
    InterstitialManager interstitialManager;
    AppOpenManager appOpenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        MaterialButton btnContinue = findViewById(R.id.btnContinue);

        interstitialManager = new InterstitialManager();
        interstitialManager.loadInterstitial(StartActivity.this);

        appOpenManager = new AppOpenManager();
        appOpenManager.loadAd(this);

        btnContinue.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            interstitialManager.showInterstitial(StartActivity.this);
        });

        FrameLayout adViewContainer = findViewById(R.id.adViewContainer);
        BannerManager bannerManager = new BannerManager(this, StartActivity.this, adViewContainer);
        bannerManager.loadBanner();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        appOpenManager.showAdIfAvailable(StartActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        appOpenManager.showAdIfAvailable(StartActivity.this);
    }

}