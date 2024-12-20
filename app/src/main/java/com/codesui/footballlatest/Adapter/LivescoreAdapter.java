package com.codesui.footballlatest.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codesui.footballlatest.R;
import com.codesui.footballlatest.activities.MatchActivity;
import com.codesui.footballlatest.ads.InterstitialManager;
import com.codesui.footballlatest.data.Match;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class LivescoreAdapter extends RecyclerView.Adapter<LivescoreAdapter.ViewHolder> {
    private final Context context;
    private final Activity activity;
    private final List<Match> matchList;
    InterstitialManager interstitialManager = new InterstitialManager();
    public LivescoreAdapter(Context context, Activity activity, List<Match> list) {
        this.context = context;
        this.activity = activity;
        this.matchList = list;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fixture, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        final Match match = this.matchList.get(position);
        holder.textHome.setText(match.getHomeTeam());
        holder.textAway.setText(match.getAwayTeam());
        holder.textResults.setText(match.getScore());
        holder.duration.setText(match.getDate().split("T")[1].split(":00Z")[0]);
        if(!Objects.equals(match.getScore(), "?-?")) {
            holder.duration.setText("FT");
        }

        Picasso.get().load(match.getCupImage()).placeholder(R.drawable.ic_football).error(R.drawable.ic_football).into(holder.imageCup);
        holder.itemView.setOnClickListener(view -> {
            Intent fixturesIntent = new Intent(LivescoreAdapter.this.context, MatchActivity.class);
            LivescoreAdapter.this.context.startActivity(fixturesIntent);
            interstitialManager.showInterstitial(LivescoreAdapter.this.activity);
        });

    }

    public int getItemCount() {
        return this.matchList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textHome;
        private final TextView textAway;
        private final TextView textResults;
        private final TextView duration;
        private final ImageView imageCup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textHome = itemView.findViewById(R.id.textHome);
            this.textAway = itemView.findViewById(R.id.textAway);
            this.textResults = itemView.findViewById(R.id.textResults);
            this.imageCup = itemView.findViewById(R.id.imageCup);
            this.duration = itemView.findViewById(R.id.duration);
        }
    }
}
