package com.codesui.footballlatest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codesui.footballlatest.R;
import com.codesui.footballlatest.Utility.Api;
import com.codesui.footballlatest.data.HomeLeague;

import java.util.List;

public class LivescoresFragment extends Fragment {

    private String competitionId = ""; // Initialize the competition ID (empty initially)
    private String url2 = ""; // URL for matches
    private List<HomeLeague> competitionList; // List for the HomeLeagues

    private ProgressBar progressBar;
    TextView textEmpty;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        return inflater.inflate(R.layout.fragment_livescores, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar);
         textEmpty = view.findViewById(R.id.textEmpty);
        // URL for home competitions
        String url1 = "https://api.football-data.org/v4/competitions";

        // Prepare RecyclerViews
        RecyclerView recyclerView1 = view.findViewById(R.id.horizontalRecyclerView);
        RecyclerView recyclerView2 = view.findViewById(R.id.recyclerView);

        // Setting up layout managers for horizontal and vertical recyclerviews
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView1.setLayoutManager(linearLayoutManager1);
        recyclerView2.setLayoutManager(linearLayoutManager2);

        // Initialize the API object
        Api api = new Api(getActivity(), getContext());

        // Load Home Competitions initially
        api.loadHomeCompetitions(url1, this,competitionId, recyclerView1);

        //String url, LivescoresFragment livescoresFragment, String competitionId, RecyclerView recyclerView) {

        // Load matches based on the current competitionId (initial load)
        //updateMatches(api, recyclerView2);
        api.loadMatches("https://api.football-data.org/v4/matches?status=LIVE", recyclerView2, progressBar, textEmpty);

        // Initialize the Adapter for home leagues
        //HomeLeagueAdapter adapter = new HomeLeagueAdapter(getContext(), this, competitionList, competitionId, recyclerView1, this); // Pass the LivescoresFragment reference here
        //recyclerView1.setAdapter(adapter);
    }

    // Method to change competitionId and reload matches
    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
        //updateMatches(new Api(getActivity(), getContext()), getView().findViewById(R.id.recyclerView));
    }

    // Helper method to update the matches based on competitionId
    private void updateMatches(Api api, RecyclerView recyclerView2) {
        if (!competitionId.isEmpty()) {
            url2 = "https://api.football-data.org/v4/competitions/" + competitionId + "/matches"; // Update the URL for the selected competition
        } else {
            url2 = "https://api.football-data.org/v4/matches"; // Use a general URL if no competitionId
        }

        // Reload matches with the new URL
        api.loadMatches(url2, recyclerView2, progressBar, textEmpty);
    }
}
