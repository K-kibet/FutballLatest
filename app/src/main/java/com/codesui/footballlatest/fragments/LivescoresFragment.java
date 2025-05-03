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

public class LivescoresFragment extends Fragment {
    RecyclerView leagueFilterRecycler;
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

        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        textEmpty = view.findViewById(R.id.textEmpty);
        // Prepare RecyclerViews
        RecyclerView recyclerView2 = view.findViewById(R.id.recyclerView);
        leagueFilterRecycler = view.findViewById(R.id.leagueFilterRecycler);


        // Setting up layout managers for horizontal and vertical recyclerviews
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        // Initialize the API object
        Api api = new Api(getActivity(), getContext());
        api.loadMatches("https://api.football-data.org/v4/matches?status=LIVE", recyclerView2, leagueFilterRecycler, progressBar, textEmpty);

    }
}
