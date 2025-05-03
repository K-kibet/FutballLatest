package com.codesui.footballlatest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.codesui.footballlatest.R;
import com.google.android.gms.ads.MobileAds;

public class SplashActivity extends AppCompatActivity {
    ProgressBar progressBar;
    int progressStatus = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        MobileAds.initialize(this);
        progressBar = findViewById(R.id.horizontal_progress);
        new Thread(() -> {
            try {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    handler.post(() -> progressBar.setProgress(progressStatus));
                    //Thread.sleep(20); // 20ms per step = 2 seconds total
                    Thread.sleep(50); // 50ms * 100 = 5000ms = 5 seconds
                }

                // Navigate after full load
                handler.post(() -> {
                    Intent intent = new Intent(SplashActivity.this, WalkthroughActivity.class);
                    startActivity(intent);
                    finish();
                });

            } catch (InterruptedException e) {
                // Handle interruption gracefully (optional logging or cleanup)
                e.printStackTrace();
            }
        }).start();


    }
}