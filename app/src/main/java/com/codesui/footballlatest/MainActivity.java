package com.codesui.footballlatest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.codesui.footballlatest.Utility.RateManager;
import com.codesui.footballlatest.Utility.ShareManager;
import com.codesui.footballlatest.Utility.UrlManager;
import com.codesui.footballlatest.ads.InterstitialManager;
import com.codesui.footballlatest.fragments.FixturesFragment;
import com.codesui.footballlatest.fragments.LeaguesFragment;
import com.codesui.footballlatest.fragments.LivescoresFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new LivescoresFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            InterstitialManager interstitialManager = new InterstitialManager();
            switch (item.getItemId()) {
                case R.id.leagues:
                    fragment = new LeaguesFragment();
                    break;
                case R.id.fixtures:
                    fragment = new FixturesFragment();
                    break;
                case R.id.more:
                    // Open the navigation drawer when "More" is clicked
                    drawer.openDrawer(GravityCompat.START); // Use GravityCompat.START if the drawer is on the left side
                    return true; // Return true to indicate event is handled
                default:
                    fragment = new LivescoresFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            interstitialManager.showInterstitial(MainActivity.this);
            return true; // Return true to indicate event is handled
        });
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        UrlManager urlManager = new UrlManager(MainActivity.this);
        switch (item.getItemId()) {
            case R.id.nav_terms:
                drawer.closeDrawer(GravityCompat.START);
                urlManager.openTerms();
                return true;
            case R.id.nav_share:
                drawer.closeDrawer(GravityCompat.START);
                ShareManager shareManager = new ShareManager(this);
                shareManager.shareApp();
                return true;
            case R.id.nav_rate:
                drawer.closeDrawer(GravityCompat.START);
                RateManager rateManager = new RateManager(this);
                rateManager.rate();
                return true;
            case R.id.nav_more_apps:
                drawer.closeDrawer(GravityCompat.START);
                urlManager.moreApps();
                return true;
            default:
                return false;
        }
    }
}
