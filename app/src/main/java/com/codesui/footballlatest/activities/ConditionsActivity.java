package com.codesui.footballlatest.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.codesui.footballlatest.R;
import com.codesui.footballlatest.ads.AppOpenManager;
import com.codesui.footballlatest.ads.BannerManager;
import com.codesui.footballlatest.ads.InterstitialManager;

public class ConditionsActivity extends AppCompatActivity {
    InterstitialManager interstitialManager;
    AppOpenManager appOpenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions);

        interstitialManager = new InterstitialManager();
        interstitialManager.loadInterstitial(ConditionsActivity.this);

        appOpenManager = new AppOpenManager();
        appOpenManager.loadAd(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        FrameLayout adViewContainer = findViewById(R.id.adViewContainer);
        BannerManager bannerManager = new BannerManager(this, ConditionsActivity.this, adViewContainer);
        bannerManager.loadBanner();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the back button click here
        if (item.getItemId() == android.R.id.home) {
            // Call your custom functions here
            finish();
            interstitialManager.showInterstitial(ConditionsActivity.this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        appOpenManager.showAdIfAvailable(ConditionsActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        appOpenManager.showAdIfAvailable(ConditionsActivity.this);
    }
}