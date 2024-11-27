package com.codesui.footballlatest.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.codesui.footballlatest.R;
import com.codesui.footballlatest.data.Standing;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.ViewHolder> {
    private final List<Standing> standingList;
    public StandingsAdapter(List<Standing> list) {
        this.standingList = list;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_standings, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        final Standing standing = this.standingList.get(position);
        holder.textPosition.setText(standing.getPosition());
        Picasso.get().load(standing.getImage()).placeholder(R.drawable.ic_football).error(R.drawable.ic_football).into(holder.imageIcon);
        holder.textName.setText(standing.getName());
        holder.textPlayed.setText(standing.getPlayed());
        holder.textDifference.setText(standing.getDifference());
        holder.textWon.setText(standing.getWon());
        holder.textDraw.setText(standing.getDraw());
        holder.textLost.setText(standing.getLost());
        holder.textPoints.setText(standing.getPoints());
    }

    public int getItemCount() {
        return this.standingList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textPosition;
        private final ImageView imageIcon;
        private final TextView textName;
        private final TextView textPlayed;
        private final TextView textDifference;
        private final TextView textWon;
        private final TextView textDraw;
        private final TextView textLost;
        private final TextView textPoints;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textPosition = itemView.findViewById(R.id.text_table_position);
            this.imageIcon = itemView.findViewById(R.id.image_team_logo);
            this.textName = itemView.findViewById(R.id.text_team_name);
            this.textPlayed= itemView.findViewById(R.id.text_team_played);
            this.textDifference = itemView.findViewById(R.id.text_team_difference);
            this.textWon = itemView.findViewById(R.id.text_team_won);
            this.textDraw = itemView.findViewById(R.id.text_team_draw);
            this.textLost = itemView.findViewById(R.id.text_team_lost);
            this.textPoints = itemView.findViewById(R.id.text_team_points);
        }
    }
}
