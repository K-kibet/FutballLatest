package com.codesui.footballlatest.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.codesui.footballlatest.Adapter.CompetitionAdapter;
import com.codesui.footballlatest.R;
import com.google.android.material.tabs.TabLayout;

public class LeagueActivity extends AppCompatActivity {

    private static final String KEY_ID = "competition_id";
    private static final String KEY_NAME = "competition_name";
    public String competitionId;
    private String competitionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league);

        // Retrieve competition details from intent or saved state
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            competitionId = intent.getStringExtra("competitionId");
            competitionName = intent.getStringExtra("competitionName");
        } else {
            competitionId = savedInstanceState.getString(KEY_ID);
            competitionName = savedInstanceState.getString(KEY_NAME);
        }

        // Setup toolbar with competition name
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(competitionName);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Setup view pager with tabs
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new CompetitionAdapter(this, getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(KEY_ID, competitionId);
        bundle.putString(KEY_NAME, competitionName);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
