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
import com.codesui.footballlatest.activities.LeagueActivity;

public class TopscorersFragment extends Fragment {
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topscorers, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LeagueActivity competitionActivity = (LeagueActivity) getActivity();
        assert competitionActivity != null;
        int competitionId = competitionActivity.competitionId;
        progressBar = view.findViewById(R.id.progressBar);
        TextView textEmpty = view.findViewById(R.id.textEmpty);

        String url = "https://api.football-data.org/v4/competitions/" + competitionId + "/scorers";
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Api api = new Api(getActivity(), getContext());
        api.loadPlayers(url, recyclerView, progressBar, textEmpty);
    }
}