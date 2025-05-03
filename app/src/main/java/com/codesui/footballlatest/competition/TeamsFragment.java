package com.codesui.footballlatest.competition;

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
import com.codesui.footballlatest.activities.FavoritesActivity;
import com.codesui.footballlatest.activities.LeagueActivity;

public class TeamsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teams, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        LeagueActivity competitionActivity = (LeagueActivity) getActivity();
        TextView textEmpty = view.findViewById(R.id.textEmpty);

        String competitionCode = competitionActivity.competitionCode;

        if (competitionCode != null && !competitionCode.isEmpty()) {
            String url = "https://api.football-data.org/v4/competitions/" + competitionCode + "/teams";
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);

            Api api = new Api(getActivity(), getContext());
            api.loadTeams(url, recyclerView, progressBar, textEmpty);
        }

    }
}