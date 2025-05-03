package com.codesui.footballlatest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.codesui.footballlatest.Adapter.CompetitionAdapter;
import com.codesui.footballlatest.R;
import com.codesui.footballlatest.ads.AppOpenManager;
import com.codesui.footballlatest.ads.BannerManager;
import com.codesui.footballlatest.ads.InterstitialManager;
import com.google.android.material.tabs.TabLayout;

public class LeagueActivity extends AppCompatActivity {
    public int competitionId;
    public String competitionCode;
    InterstitialManager interstitialManager;
    AppOpenManager appOpenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league);

        interstitialManager = new InterstitialManager();
        interstitialManager.loadInterstitial(LeagueActivity.this);

        appOpenManager = new AppOpenManager();
        appOpenManager.loadAd(this);

        // Only use intent data
        Intent intent = getIntent();
        competitionId = intent.getIntExtra("competitionId", -1);
        String competitionName = intent.getStringExtra("competitionName");
        competitionCode = intent.getStringExtra("competitionCode");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(competitionName);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new CompetitionAdapter(this, getSupportFragmentManager(), competitionId, competitionCode));

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        FrameLayout adViewContainer = findViewById(R.id.adViewContainer);
        BannerManager bannerManager = new BannerManager(this, LeagueActivity.this, adViewContainer);
        bannerManager.loadBanner();
    }

    @Override
    public boolean onSupportNavigateUp() {
        interstitialManager.showInterstitial(LeagueActivity.this);
        finish();
        return true;
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        appOpenManager.showAdIfAvailable(LeagueActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        appOpenManager.showAdIfAvailable(LeagueActivity.this);
    }
}
