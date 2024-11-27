package com.codesui.footballlatest.Adapter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.codesui.footballlatest.R;
import com.codesui.footballlatest.data.HomeLeague;
import com.codesui.footballlatest.fragments.LivescoresFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeLeagueAdapter extends RecyclerView.Adapter<HomeLeagueAdapter.ViewHolder> {

    private Context context;
    private List<HomeLeague> homeLeagues;
    private String selectedCompetitionId;
    private RecyclerView recyclerView;
    private int selectedPosition = 0;
    private LivescoresFragment livescoresFragment; // Reference to the fragment

    public HomeLeagueAdapter(Context context, LivescoresFragment fragment, List<HomeLeague> homeLeagues, String selectedCompetitionId, RecyclerView recyclerView) {
        this.context = context;
        this.homeLeagues = homeLeagues;
        this.selectedCompetitionId = selectedCompetitionId;
        this.recyclerView = recyclerView;
        this.livescoresFragment =  fragment; // Set the fragment reference
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_league, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HomeLeague homeLeague = homeLeagues.get(position);
        holder.leagueName.setText(homeLeague.getLeagueName());
        // Using Picasso for loading the league emblem
        Picasso.get()
                .load(homeLeague.getLeagueImage())
                .placeholder(R.drawable.ic_epl_banner)
                .error(R.drawable.ic_epl_banner)
                .into(holder.leagueEmblem);

        // Set item click listener to update the competitionId and reload matches
        holder.itemView.setOnClickListener(v -> {
            selectedCompetitionId = homeLeague.getLeagueId();
            livescoresFragment.setCompetitionId(selectedCompetitionId); // Update competitionId in fragment

            // Move the selected item to the first position
            setActiveAndMoveToFirst(position);
            selectedCompetitionId = homeLeagues.get(selectedPosition).getLeagueId();

            notifyDataSetChanged(); // Notify the adapter to refresh UI
        });

        // Highlight the selected league
        /*if (homeLeague.getLeagueId().equals(selectedCompetitionId)) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
        } else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.white));
        }*/

        // Set background color based on whether the item is selected
        if (position == selectedPosition) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.light_gray)); // Set selected color
        }

    }

    // Method to move the clicked item to the first position and update the active state
    private void setActiveAndMoveToFirst(int position) {
        if (position != selectedPosition) {
            // Mark all items as inactive
            for (HomeLeague league : homeLeagues) {
                league.setActive(false);
            }

            // Get the HomeLeague object at the clicked position and mark it as active
            HomeLeague selectedItem = homeLeagues.get(position);
            selectedItem.setActive(true);  // Set active

            // Remove the selected item from its original position and add it to the first position
            homeLeagues.remove(position);
            homeLeagues.add(0, selectedItem);

            selectedPosition = 0; // Update selected position
            notifyDataSetChanged(); // Refresh the RecyclerView

            notifyItemChanged(position);

            // Scroll to the last item in the list
            recyclerView.scrollToPosition(0);
        }
    }

    @Override
    public int getItemCount() {
        return homeLeagues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView leagueName;
        ImageView leagueEmblem;

        public ViewHolder(View itemView) {
            super(itemView);
            leagueName = itemView.findViewById(R.id.leagueName);
            leagueEmblem = itemView.findViewById(R.id.leagueImage);
        }
    }
}
