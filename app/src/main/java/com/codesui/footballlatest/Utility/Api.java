package com.codesui.footballlatest.Utility;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.codesui.footballlatest.Adapter.FixturesAdapter;
import com.codesui.footballlatest.Adapter.HomeLeagueAdapter;
import com.codesui.footballlatest.Adapter.LeaguesAdapter;
import com.codesui.footballlatest.Adapter.PlayersAdapter;
import com.codesui.footballlatest.Adapter.StandingsAdapter;
import com.codesui.footballlatest.Adapter.TeamsAdapter;
import com.codesui.footballlatest.MainActivity;
import com.codesui.footballlatest.R;
import com.codesui.footballlatest.data.HomeLeague;
import com.codesui.footballlatest.data.League;
import com.codesui.footballlatest.data.Match;
import com.codesui.footballlatest.data.Player;
import com.codesui.footballlatest.data.Standing;
import com.codesui.footballlatest.data.Team;
import com.codesui.footballlatest.fragments.LivescoresFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class Api {
    Activity activity;
    Context context;
    public Api(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public void loadHomeCompetitions (String url, LivescoresFragment livescoresFragment, String competitionId, RecyclerView recyclerView) {

        List<HomeLeague> homeLeagueList = new ArrayList<>();
        //public HomeLeagueAdapter(Context context, LivescoresFragment fragment, List<HomeLeague> homeLeagues, String selectedCompetitionId, RecyclerView recyclerView) {
        HomeLeagueAdapter homeLeagueAdapter = new HomeLeagueAdapter(context, livescoresFragment, homeLeagueList, competitionId,recyclerView);
        recyclerView.setAdapter(homeLeagueAdapter);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("competitions");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            HomeLeague item = new HomeLeague(
                                    jsonArray.getJSONObject(i).getString("name"),
                                    jsonArray.getJSONObject(i).getString("id"),
                                    jsonArray.getJSONObject(i).getString("emblem"), false);
                            homeLeagueList.add(item);
                        }
                        homeLeagueAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        //alertDialog.show();
                    }
                }, error -> {
                    //alertDialog.show();
                }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String>  params = new HashMap<>();
                params.put("X-Auth-Token", "6f290c7d3caf4bb69f07dacaf7273267");
                params.put("Accept", "application/json");
                return params;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    public void loadCompetitions (String url, RecyclerView recyclerView, ProgressBar progressBar, TextView textEmpty) {
        List<League> leagueList = new ArrayList<>();
        LeaguesAdapter leaguesAdapter = new LeaguesAdapter(context, activity, leagueList);
        recyclerView.setAdapter(leaguesAdapter);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("competitions");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            League item = new League(
                                    jsonArray.getJSONObject(i).getJSONObject("area").getString("name"),
                                    jsonArray.getJSONObject(i).getString("name"),
                                    jsonArray.getJSONObject(i).getString("id"),
                                    jsonArray.getJSONObject(i).getString("code"),
                                    jsonArray.getJSONObject(i).getString("emblem"));
                            leagueList.add(item);
                        }
                        leaguesAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        if(leagueList.isEmpty()) {
                            textEmpty.setText(R.string.no_leagues_found);
                            textEmpty.setVisibility(View.VISIBLE);
                        } else {
                            textEmpty.setVisibility(View.GONE); // Hide emptyText if data is present
                        }
                    } catch (JSONException e) {
                        progressBar.setVisibility(View.GONE);
                        if(leagueList.isEmpty()) {
                            textEmpty.setText(R.string.error_leagues);
                            textEmpty.setVisibility(View.VISIBLE);
                        }
                    }
                }, error -> {
                    //alertDialog.show();
                    progressBar.setVisibility(View.GONE);
                    if(leagueList.isEmpty()) {
                        textEmpty.setText(R.string.error_leagues);
                        textEmpty.setVisibility(View.VISIBLE);
                    }
                }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String>  params = new HashMap<>();
                params.put("X-Auth-Token", "6f290c7d3caf4bb69f07dacaf7273267");
                params.put("Accept", "application/json");
                return params;
            }
        };
        progressBar.setVisibility(View.VISIBLE);
        textEmpty.setVisibility(View.GONE);
        requestQueue.add(jsonObjectRequest);
    }

    public void loadTeams(String url, RecyclerView recyclerView, ProgressBar progressBar, TextView textEmpty) {
        List<Team> teamList = new ArrayList<>();
        TeamsAdapter teamsAdapter = new TeamsAdapter(teamList);
        recyclerView.setAdapter(teamsAdapter);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("teams");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Team team = new Team(jsonObject.getString("name"),
                                    jsonObject.getString("crest"),
                                    jsonObject.getInt("id"), false);
                            teamList.add(team);
                        }
                        teamsAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        if(teamList.isEmpty()) {
                            textEmpty.setText(R.string.no_teams_found);
                            textEmpty.setVisibility(View.VISIBLE);
                        } else {
                            textEmpty.setVisibility(View.GONE); // Hide emptyText if data is present
                        }

                    } catch (JSONException e) {
                        //dialogManager.showDialog();
                        progressBar.setVisibility(View.GONE);
                        if(teamList.isEmpty()) {
                            textEmpty.setText(R.string.error_teams);
                            textEmpty.setVisibility(View.VISIBLE);
                        }
                    }
                }, error -> {
                    //dialogManager.showDialog();
                    progressBar.setVisibility(View.GONE);
                    if(teamList.isEmpty()) {
                        textEmpty.setText(R.string.error_teams);
                        textEmpty.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("X-Auth-Token", "6f290c7d3caf4bb69f07dacaf7273267");
                return params;
            }
        };
        progressBar.setVisibility(View.VISIBLE);
        textEmpty.setVisibility(View.GONE);
        requestQueue.add(jsonObjectRequest);
    }


    public void loadStandings(String url, RecyclerView recyclerView, ProgressBar progressBar, TextView textEmpty) {
        List<Standing> standingList = new ArrayList<>();
        StandingsAdapter standingsAdapter = new StandingsAdapter(standingList);
        recyclerView.setAdapter(standingsAdapter);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("standings").getJSONObject(0).getJSONArray("table");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Standing standing = new Standing(
                                    jsonObject.getString("position"),
                                    jsonObject.getJSONObject("team").getString("crest"),
                                    jsonObject.getJSONObject("team").getString("shortName"),
                                    jsonObject.getString("playedGames"),
                                    jsonObject.getString("won"),
                                    jsonObject.getString("draw"),
                                    jsonObject.getString("lost"),
                                    jsonObject.getString("goalDifference"),
                                    jsonObject.getString("points"));
                            standingList.add(standing);
                        }
                        standingsAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        if(standingList.isEmpty()) {
                            textEmpty.setText(R.string.no_standings_found);
                            textEmpty.setVisibility(View.VISIBLE);
                        } else {
                            textEmpty.setVisibility(View.GONE); // Hide emptyText if data is present
                        }
                    } catch (JSONException e) {
                        //dialogManager.showDialog();
                        progressBar.setVisibility(View.GONE);
                        if(standingList.isEmpty()) {
                            textEmpty.setText(R.string.error_standings);
                            textEmpty.setVisibility(View.VISIBLE);
                        }
                    }
                }, error ->{
                    //dialogManager.showDialog();
                    progressBar.setVisibility(View.GONE);
                    if(standingList.isEmpty()) {
                        textEmpty.setText(R.string.error_standings);
                        textEmpty.setVisibility(View.VISIBLE);
                    }
                } ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("X-Auth-Token", "6f290c7d3caf4bb69f07dacaf7273267");
                return params;
            }
        };
        progressBar.setVisibility(View.VISIBLE);
        textEmpty.setVisibility(View.GONE);
        requestQueue.add(jsonObjectRequest);
    }

    public void loadPlayers(String url, RecyclerView recyclerView, ProgressBar progressBar, TextView textEmpty) {
        List<Player> playerList = new ArrayList<>();
        PlayersAdapter playersAdapter = new PlayersAdapter(playerList);
        recyclerView.setAdapter(playersAdapter);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        // Parse the "scorers" JSON array from the response
                        JSONArray jsonArray = response.getJSONArray("scorers");

                        // Loop through the array of scorers
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            // Extract player details and create Player object
                            Player player = new Player(
                                    jsonObject.getJSONObject("player").getString("id"),
                                    jsonObject.getJSONObject("player").getString("name"),
                                    jsonObject.getString("goals"), // Consider converting to integer if necessary
                                    jsonObject.getJSONObject("team").getString("crest")
                            );

                            // Add the player to the list
                            playerList.add(player);
                        }
                        playersAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        if(playerList.isEmpty()) {
                            textEmpty.setText(R.string.no_topscoreres_found);
                            textEmpty.setVisibility(View.VISIBLE);
                        } else {
                            textEmpty.setVisibility(View.GONE); // Hide emptyText if data is present
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();  // Log the error
                        progressBar.setVisibility(View.GONE);
                        if(playerList.isEmpty()) {
                            textEmpty.setText(R.string.error_topscorers);
                            textEmpty.setVisibility(View.VISIBLE);
                        }
                    }
                }, error -> {
                    progressBar.setVisibility(View.GONE);
                    if(playerList.isEmpty()) {
                        textEmpty.setText(R.string.error_topscorers);
                        textEmpty.setVisibility(View.VISIBLE);
                    }
                }) {

            // Add required headers (e.g., Authorization token)
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("X-Auth-Token", "6f290c7d3caf4bb69f07dacaf7273267"); // Keep your token secure!
                return params;
            }
        };
        progressBar.setVisibility(View.VISIBLE);
        textEmpty.setVisibility(View.GONE);
        // Add the request to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }

    public void loadMatches(String url, RecyclerView recyclerView, ProgressBar progressBar, TextView textEmpty) {
        List<Match> matchList = new ArrayList<>();
        FixturesAdapter fixturesAdapter = new FixturesAdapter(context, activity, matchList);
        recyclerView.setAdapter(fixturesAdapter);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("matches");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Match match = new Match(
                                    jsonObject.getString("id"),
                                    jsonObject.getJSONObject("homeTeam").getString("shortName"),
                                    jsonObject.getJSONObject("awayTeam").getString("shortName"),
                                    jsonObject.getJSONObject("homeTeam").getString("crest"),
                                    jsonObject.getJSONObject("awayTeam").getString("crest"),
                                    jsonObject.getJSONObject("competition").getString("emblem"),
                                    jsonObject.getJSONObject("score").getJSONObject("fullTime").getString("home"),
                                    jsonObject.getJSONObject("score").getJSONObject("fullTime").getString("away"),
                                    jsonObject.getString("utcDate"), jsonObject.getString("status"));
                            if (jsonObject.getJSONObject("score").getJSONObject("fullTime").getString("home").equals("null")) {
                                match.setHomeScore("?");
                                match.setAwayScore("?");
                            }

                            matchList.add(match);
                        }
                        fixturesAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        if(matchList.isEmpty()) {
                            textEmpty.setText(R.string.no_matches_found);
                            textEmpty.setVisibility(View.VISIBLE);
                        } else {
                            textEmpty.setVisibility(View.GONE); // Hide emptyText if data is present
                        }

                    } catch (JSONException e) {
                        progressBar.setVisibility(View.GONE);
                        if(matchList.isEmpty()) {
                            textEmpty.setText(R.string.error_matches);
                            textEmpty.setVisibility(View.VISIBLE);
                        }
                    }
                }, error -> {
                    progressBar.setVisibility(View.GONE);
                    if(matchList.isEmpty()) {
                        textEmpty.setText(R.string.error_matches);
                        textEmpty.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("X-Auth-Token", "6f290c7d3caf4bb69f07dacaf7273267");
                params.put("X-Unfold-Goals", "true");
                return params;
            }
        };
        progressBar.setVisibility(View.VISIBLE);
        textEmpty.setVisibility(View.GONE);
        requestQueue.add(jsonObjectRequest);
    }
}
