package com.codesui.footballlatest.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.codesui.footballlatest.R;
import com.codesui.footballlatest.ads.BannerManager;
import com.codesui.footballlatest.ads.InterstitialManager;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class MatchActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private View main;
    private View emptyView;
    private TextView homeTeam, awayTeam, homeFormation, awayFormation, textLeague, homeResult, awayResult, textResults;
    private ImageView homeImage, awayImage, imageLeague;
    ActionBar actionBar;

    private TextView home_ball_possession,  home_shots,   home_shots_on_goal,  home_shots_off_goal, home_corner_kicks,
            home_free_kicks, home_goal_kicks, home_offsides, home_fouls, home_saves, home_throw_ins, home_yellow_cards, home_yellow_red_cards, home_red_cards;
    private TextView away_ball_possession, away_shots, away_shots_on_goal, away_shots_off_goal, away_corner_kicks,
            away_free_kicks, away_goal_kicks, away_offsides, away_fouls, away_saves, away_throw_ins, away_yellow_cards, away_yellow_red_cards, away_red_cards;

    MaterialCardView cardLive;
    private LinearLayout stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        // Setup toolbar with competition name
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String url = "https://api.football-data.org/v4/matches/" + id;

        InterstitialManager interstitialManager = new InterstitialManager();
        interstitialManager.loadInterstitial(MatchActivity.this);

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        progressBar = findViewById(R.id.progressBar);
        main = findViewById(R.id.main);
        emptyView = findViewById(R.id.emptyView);
        homeTeam = findViewById(R.id.homeTeam);
        awayTeam = findViewById(R.id.awayTeam);
        homeImage = findViewById(R.id.homeImage);
        awayImage = findViewById(R.id.awayImage);

        homeFormation = findViewById(R.id.homeFormation);
        awayFormation = findViewById(R.id.awayFormation);
        imageLeague = findViewById(R.id.imageLeague);
        textLeague = findViewById(R.id.textLeague);
        homeResult = findViewById(R.id.homeResult);
        awayResult = findViewById(R.id.awayResult);

        textResults = findViewById(R.id.textResults);

        stats = findViewById(R.id.stats);

        home_ball_possession = findViewById(R.id.home_ball_possession);
        away_ball_possession = findViewById(R.id.away_ball_possession);
        home_shots = findViewById(R.id.home_shots);
        away_shots = findViewById(R.id.away_shots);
        home_shots_on_goal = findViewById(R.id.home_shots_on_goal);
        away_shots_on_goal = findViewById(R.id.away_shots_on_goal);
        home_shots_off_goal = findViewById(R.id.home_shots_off_goal);
        away_shots_off_goal = findViewById(R.id.away_shots_off_goal);
        home_corner_kicks = findViewById(R.id.home_corner_kicks);
        away_corner_kicks = findViewById(R.id.away_corner_kicks);
        home_free_kicks = findViewById(R.id.home_free_kicks);
        away_free_kicks = findViewById(R.id.away_free_kicks);
        home_goal_kicks = findViewById(R.id.home_goal_kicks);
        away_goal_kicks = findViewById(R.id.away_goal_kicks);
        home_offsides = findViewById(R.id.home_offsides);
        away_offsides = findViewById(R.id.away_offsides);
        home_fouls = findViewById(R.id.home_fouls);
        away_fouls = findViewById(R.id.away_fouls);
        home_saves = findViewById(R.id.home_saves);
        away_saves = findViewById(R.id.away_saves);
        home_throw_ins = findViewById(R.id.home_throw_ins);
        away_throw_ins = findViewById(R.id.away_throw_ins);
        home_yellow_cards = findViewById(R.id.home_yellow_cards);
        away_yellow_cards = findViewById(R.id.away_yellow_cards);
        home_yellow_red_cards = findViewById(R.id.home_yellow_red_cards);
        away_yellow_red_cards = findViewById(R.id.away_yellow_red_cards);
        home_red_cards = findViewById(R.id.home_red_cards);
        away_red_cards = findViewById(R.id.away_red_cards);

        fetchMatchData(url);

        FrameLayout adViewContainer = findViewById(R.id.adViewContainer);
        BannerManager bannerManager = new BannerManager(this, MatchActivity.this, adViewContainer);
        bannerManager.loadBanner();
        cardLive = findViewById(R.id.cardLive);

        cardLive.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MatchActivity.this);
            View layout_dialog = LayoutInflater.from(MatchActivity.this).inflate(R.layout.error_dialog, null);
            builder.setView(layout_dialog);
            Button btnOk = layout_dialog.findViewById(R.id.btnOk);
            AlertDialog dialog = builder.create();
            dialog.setCancelable(true);
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.show();

            btnOk.setOnClickListener(V -> {
                dialog.cancel();
                interstitialManager.showInterstitial(MatchActivity.this);
            });
        });
    }

    private void fetchMatchData(String url) {
        // Show loading spinner
        progressBar.setVisibility(View.VISIBLE);
        main.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);

        // Create a JsonObjectRequest to fetch match data
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
                    @Override
                    public void onResponse(JSONObject response) {
                        // Hide the progress bar and show the main content
                        progressBar.setVisibility(View.GONE);
                        main.setVisibility(View.VISIBLE);


                        // Parse competition data safely
                        JSONObject competitionObj = response.optJSONObject("competition");
                        if (competitionObj != null) {
                            actionBar.setTitle(competitionObj.optString("name", "Match Details"));

                            String competitionCrestUrl = competitionObj.optString("emblem", "defaultImageURL");
                            Picasso.get().load(competitionCrestUrl).placeholder(R.drawable.ic_epl_banner).error(R.drawable.ic_epl_banner).into(imageLeague);
                        }

                        // Parse home team data safely
                        JSONObject homeTeamObj = response.optJSONObject("homeTeam");
                        if (homeTeamObj != null) {
                            String homeTeamName = homeTeamObj.optString("shortName", "Unknown Team");
                            String homeTeamFormation = homeTeamObj.optString("formation", "F-F-F-F");
                            homeTeam.setText(homeTeamName);
                            homeFormation.setText(homeTeamFormation);

                            String homeCrestUrl = homeTeamObj.optString("crest", "defaultImageURL");
                            Picasso.get().load(homeCrestUrl).placeholder(R.drawable.ic_epl_banner).error(R.drawable.ic_epl_banner).into(homeImage);
                        }

                        // Parse away team data safely
                        JSONObject awayTeamObj = response.optJSONObject("awayTeam");
                        if (awayTeamObj != null) {
                            String awayTeamName = awayTeamObj.optString("shortName", "Unknown Team");
                            String awayTeamFormation = awayTeamObj.optString("formation", "F-F-F-F");
                            awayTeam.setText(awayTeamName);
                            awayFormation.setText(awayTeamFormation);

                            String awayCrestUrl = awayTeamObj.optString("crest", "defaultImageURL");
                            Picasso.get().load(awayCrestUrl).placeholder(R.drawable.ic_epl_banner).error(R.drawable.ic_epl_banner).into(awayImage);
                        }


                        String status = response.optString("status", "CANCELLED");
                        JSONObject scoreObj = response.optJSONObject("score");

                        if(status.equals("LIVE") || status.equals("FINISHED") || status.equals("IN_PLAY") || status.equals("PAUSED")){

                            String minute = response.optString("minute", "VS");

                            if(status.equals("FINISHED")){
                                textResults.setText("FT");
                                textLeague.setText(status);
                            } else {
                                textResults.setText(minute + "'");
                            }

                            // Parse score data safely

                            if (scoreObj != null) {
                                JSONObject fullTime = scoreObj.optJSONObject("fullTime");
                                if (fullTime != null) {
                                    String homeGoals = fullTime.optString("home", "?");
                                    String awayGoals = fullTime.optString("away", "?");
                                    homeResult.setText(homeGoals);
                                    awayResult.setText(awayGoals);
                                }
                            }

                            //stats.setVisibility(View.VISIBLE);

                            JSONObject homeStats = homeTeamObj.optJSONObject("statistics");
                            if(homeStats != null) {
                                Toast.makeText(MatchActivity.this, homeStats.toString(), Toast.LENGTH_SHORT).show();
                               home_ball_possession.setText(homeStats.optString("ball_possession", "0"));
                                home_shots.setText(homeStats.optString("shots", "0"));
                                home_shots_on_goal.setText(homeStats.optString("shots_on_goal", "0"));
                                home_shots_off_goal.setText(homeStats.optString("shots_off_goal", "0"));
                                home_corner_kicks.setText(homeStats.optString("corner_kicks", "0"));
                                home_free_kicks.setText(homeStats.optString("free_kicks", "0"));
                                home_goal_kicks.setText(homeStats.optString("goal_kicks", "0"));
                                home_offsides.setText(homeStats.optString("offsides", "0"));
                                home_fouls.setText(homeStats.optString("fouls", "0"));
                                home_saves.setText(homeStats.optString("saves", "0"));
                                home_throw_ins.setText(homeStats.optString("throw_ins", "0"));
                                home_yellow_cards.setText(homeStats.optString("yellow_cards", "0"));
                                home_yellow_red_cards.setText(homeStats.optString("yellow_red_cards", "0"));
                                home_red_cards.setText(homeStats.optString("red_cards", "0"));
                            }


                            assert awayTeamObj != null;
                            JSONObject awayStats = awayTeamObj.optJSONObject("statistics");
                            if(awayStats != null) {
                                away_ball_possession.setText(awayStats.optString("ball_possession", "0"));
                                away_shots.setText(awayStats.optString("shots", "0"));
                                away_shots_on_goal.setText(awayStats.optString("shots_on_goal", "0"));
                                away_shots_off_goal.setText(awayStats.optString("shots_off_goal", "0"));
                                away_corner_kicks.setText(awayStats.optString("corner_kicks", "0"));
                                away_free_kicks.setText(awayStats.optString("free_kicks", "0"));
                                away_goal_kicks.setText(awayStats.optString("goal_kicks", "0"));
                                away_offsides.setText(awayStats.optString("offsides", "0"));
                                away_fouls.setText(awayStats.optString("fouls", "0"));
                                away_saves.setText(awayStats.optString("saves", "0"));
                                away_throw_ins.setText(awayStats.optString("throw_ins", "0"));
                                away_yellow_cards.setText(awayStats.optString("yellow_cards", "0"));
                                away_yellow_red_cards.setText(awayStats.optString("yellow_red_cards", "0"));
                                away_red_cards.setText(awayStats.optString("red_cards", "0"));
                            }

                        } else {
                            textResults.setText("VS");
                            //stats.setVisibility(View.GONE);
                            if(status.equals("SCHEDULED") || status.equals("TIMED")){
                                String formattedDate = convertDate(response.optString("utcDate", ""));
                                textLeague.setText(formattedDate);
                            } else {
                                textLeague.setText(status);
                                textLeague.setTextColor(R.color.red);
                            }

                            homeResult.setText("?");
                            awayResult.setText("?");

                            cardLive.setClickable(false);
                        }

                    }
                }, error -> {
                    Log.e("Volley Error", "Error fetching data", error);
                    showErrorView();
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("X-Auth-Token", "6f290c7d3caf4bb69f07dacaf7273267");
                params.put("Accept", "application/json");
                return params;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }



    private String convertDate(String utcDateString) {
        try {
            // Define the UTC date format (input string format)
            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // Ensure the date is parsed in UTC time zone

            // Parse the UTC date string into a Date object
            Date utcDate = utcFormat.parse(utcDateString);

            // Define the output format (including both date and time)
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
            outputFormat.setTimeZone(TimeZone.getDefault()); // Use the device's local time zone for display

            // Format the Date into the required format (date and time)
            String formattedDateTime = outputFormat.format(utcDate);
            return formattedDateTime;
        } catch (Exception e) {
            e.printStackTrace();
            return utcDateString;  // In case of an error, return the original string (or handle accordingly)
        }
    }



    private void showErrorView() {
        progressBar.setVisibility(View.GONE);
        main.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Error fetching data!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}