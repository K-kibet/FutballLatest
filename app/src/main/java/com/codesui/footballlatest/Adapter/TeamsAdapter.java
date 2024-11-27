package com.codesui.footballlatest.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codesui.footballlatest.R;
import com.codesui.footballlatest.data.Team;
import com.squareup.picasso.Picasso;

import java.util.List;


public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {
    private final List<Team> teamList;
    public TeamsAdapter(List<Team> list) {
        this.teamList = list;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        final Team team = this.teamList.get(position);
        Picasso.get().load(team.getImage()).placeholder(R.drawable.ic_football).error(R.drawable.ic_football).into(holder.imageTeam);
        holder.textTeam.setText(team.getName());
        if (team.isFavorite()) {
            holder.buttonFavorite.setImageResource(R.drawable.baseline_star_24); // Filled star
        } else {
            holder.buttonFavorite.setImageResource(R.drawable.baseline_star_outline_24); // Outline star
        }

        holder.itemView.setOnClickListener(view -> {
            boolean newFavoriteState = !team.isFavorite();  // Toggle state
            team.setFavorite(newFavoriteState);
            if (newFavoriteState) {
                holder.buttonFavorite.setImageResource(R.drawable.baseline_star_24); // Filled star
            } else {
                holder.buttonFavorite.setImageResource(R.drawable.baseline_star_outline_24); // Outline star
            }
        });
    }

    public int getItemCount() {
        return this.teamList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageTeam;
        private final TextView textTeam;
        private final ImageView buttonFavorite;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageTeam = itemView.findViewById(R.id.imageTeam);
            this.textTeam = itemView.findViewById(R.id.textTeam);
            this.buttonFavorite = itemView.findViewById(R.id.buttonFavorite);
        }
    }
}