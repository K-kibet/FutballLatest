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
import com.codesui.footballlatest.activities.LeagueActivity;
import com.codesui.footballlatest.ads.InterstitialManager;
import com.codesui.footballlatest.data.League;
import com.squareup.picasso.Picasso;
import java.util.List;

public class LeaguesAdapter extends RecyclerView.Adapter<LeaguesAdapter.ViewHolder> {
    private final Context context;
    private final Activity activity;
    private final List<League> competitionList;
    InterstitialManager interstitialManager = new InterstitialManager();

    public LeaguesAdapter(Context context, Activity activity, List<League> list) {
        this.context = context;
        this.activity = activity;
        this.competitionList = list;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_league, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        final League league = this.competitionList.get(position);
        holder.leagueName.setText(league.getLeagueName());
        holder.leagueArena.setText(league.getLeagueArena());
        Picasso.get().load(league.getLeagueImage()).placeholder(R.drawable.ic_epl_banner).error(R.drawable.ic_epl_banner).into(holder.competitionImage);
        holder.itemView.setOnClickListener(view -> {
            Intent leagueIntent = new Intent(LeaguesAdapter.this.context, LeagueActivity.class);
            leagueIntent.putExtra("competitionName", league.getLeagueArena());
            leagueIntent.putExtra("competitionId", league.getLeagueId());
            LeaguesAdapter.this.context.startActivity(leagueIntent);
            interstitialManager.showInterstitial(LeaguesAdapter.this.activity);
        });

    }

    public int getItemCount() {
        return this.competitionList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView leagueName;
        private final TextView leagueArena;
        private final ImageView competitionImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.leagueName = itemView.findViewById(R.id.competitionName);
            this.leagueArena = itemView.findViewById(R.id.competitionArena);
            this.competitionImage = itemView.findViewById(R.id.competitionImage);
        }
    }
}