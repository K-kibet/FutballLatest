package com.codesui.footballlatest.activities;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codesui.footballlatest.Adapter.StartedAdapter;
import com.codesui.footballlatest.MainActivity;
import com.codesui.footballlatest.R;
import com.codesui.footballlatest.Utility.RateManager;
import com.codesui.footballlatest.Utility.ShareManager;
import com.codesui.footballlatest.Utility.UrlManager;
import com.codesui.footballlatest.ads.AppOpenManager;
import com.codesui.footballlatest.data.StartedItem;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class StartedActivity extends AppCompatActivity {
    AppOpenManager appOpenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        appOpenManager = new AppOpenManager();
        appOpenManager.loadAd(this);

        List<StartedItem> itemList = new ArrayList<>();
        itemList.add(new StartedItem(R.drawable.baseline_play_circle_outline_24, "Let's Get Started", item -> {
            Intent intent = new Intent(StartedActivity.this, MainActivity.class);
            startActivity(intent);
            appOpenManager.showAdIfAvailable(StartedActivity.this);
        }));
        itemList.add(new StartedItem(R.drawable.baseline_star_rate_24, "Rate Us", item -> {
            RateManager rateManager = new RateManager(this);
            rateManager.rate();
        }));
        itemList.add(new StartedItem(R.drawable.baseline_description_24, "Privacy Policy", item -> {
            UrlManager urlManager = new UrlManager(StartedActivity.this);
            urlManager.openTerms();
        }));

        itemList.add(new StartedItem(R.drawable.baseline_share_24, "Share App", item -> {
            ShareManager shareManager = new ShareManager(this);
            shareManager.shareApp();
        }));

        StartedAdapter adapter = new StartedAdapter(this, itemList);
        recyclerView.setAdapter(adapter);


        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitBottomSheet(); // Custom method
            }
        });
    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        showExitBottomSheet();
    }*/


    private void showExitBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = getLayoutInflater().inflate(R.layout.exit_bottom_sheet, null);
        bottomSheetDialog.setContentView(sheetView);

        Button btnYes = sheetView.findViewById(R.id.btnExitYes);
        Button btnNo = sheetView.findViewById(R.id.btnExitNo);

        btnYes.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            finish(); // Close the app
        });

        btnNo.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.show();
    }

}