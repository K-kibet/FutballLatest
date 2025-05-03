package com.codesui.footballlatest.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codesui.footballlatest.Adapter.LanguagesAdapter;
import com.codesui.footballlatest.R;
import com.codesui.footballlatest.Utility.JsonResource;
import com.codesui.footballlatest.ads.AppOpenManager;
import com.codesui.footballlatest.ads.BannerManager;
import com.codesui.footballlatest.ads.InterstitialManager;
import com.codesui.footballlatest.data.Language;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LanguageActivity extends AppCompatActivity {
    InterstitialManager interstitialManager;
    AppOpenManager appOpenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        interstitialManager = new InterstitialManager();
        interstitialManager.loadInterstitial(LanguageActivity.this);


        appOpenManager = new AppOpenManager();
        appOpenManager.loadAd(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        List<Language> languageList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        LanguagesAdapter languagesAdapter = new LanguagesAdapter(languageList);

        try {
            JsonResource jsonResource = new JsonResource();
            // Load and parse the JSON file
            String jsonData = jsonResource.loadJSONFromRawResource(this, R.raw.languages); // Load from raw folder
            JSONArray jsonArray = new JSONArray(jsonData);

            // Access elements from the JSONArray
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Language language = new Language(jsonObject.getString("code"),
                        jsonObject.getString("name"),
                        jsonObject.getString("nativeName"), false);
                languageList.add(language);
            }

            recyclerView.setAdapter(languagesAdapter);
            languagesAdapter.notifyDataSetChanged(); // Notify the adapter of data changes

        } catch (JSONException e) {
            e.printStackTrace();
        }

        languageList.forEach(lang -> {
            lang.setSelected(false);
        });


        FrameLayout adViewContainer = findViewById(R.id.adViewContainer);
        BannerManager bannerManager = new BannerManager(this, LanguageActivity.this, adViewContainer);
        bannerManager.loadBanner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_icon) {
            Intent intent = new Intent(LanguageActivity.this, FavoritesActivity.class);
            interstitialManager.showInterstitial(LanguageActivity.this);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        appOpenManager.showAdIfAvailable(LanguageActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        appOpenManager.showAdIfAvailable(LanguageActivity.this);
    }
}