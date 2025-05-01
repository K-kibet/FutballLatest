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

        /*new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, WalkthroughActivity.class));
            finish();
        }, 1000);*/


        /*new Thread(() -> {
            while (progressStatus < 100) {
                progressStatus += 1;
                handler.post(() -> progressBar.setProgress(progressStatus));
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Navigate after progress reaches 100
            handler.post(() -> {
                Intent intent = new Intent(SplashActivity.this, WalkthroughActivity.class);
                startActivity(intent);
                finish(); // Optional: close MainActivity
            });
        }).start();*/

        new Thread(() -> {
            try {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    handler.post(() -> progressBar.setProgress(progressStatus));
                    Thread.sleep(20); // 20ms per step = 2 seconds total
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