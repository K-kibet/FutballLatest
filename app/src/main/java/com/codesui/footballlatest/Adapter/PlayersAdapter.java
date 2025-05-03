package com.codesui.footballlatest.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codesui.footballlatest.R;
import com.codesui.footballlatest.data.Player;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {
    private final List<Player> playerList;

    public PlayersAdapter(List<Player> list) {
        this.playerList = list;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topscorer, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        final Player player = this.playerList.get(position);
        holder.position.setText(String.valueOf(position + 1));
        holder.name.setText(player.getName());
        holder.goals.setText(player.getGoals());
        Picasso.get().load(player.getTeamImage()).placeholder(R.drawable.image5).error(R.drawable.image5).into(holder.team);
    }

    public int getItemCount() {
        return this.playerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView position;
        private final TextView name;
        private final TextView goals;
        private final ImageView team;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.position = itemView.findViewById(R.id.position);
            this.name = itemView.findViewById(R.id.name);
            this.goals = itemView.findViewById(R.id.goals);
            this.team = itemView.findViewById(R.id.team);
        }
    }
}