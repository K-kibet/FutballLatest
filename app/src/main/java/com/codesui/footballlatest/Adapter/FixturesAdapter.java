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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class FixturesAdapter extends RecyclerView.Adapter<FixturesAdapter.ViewHolder> {
    private final Context context;
    private final Activity activity;
    private final List<Match> matchList;
    InterstitialManager interstitialManager = new InterstitialManager();
    public FixturesAdapter(Context context, Activity activity, List<Match> list) {
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
        holder.date.setText(formatDateWithSuffix(match.getDate()));
        String dateTime = match.getDate();
        //String time = dateTime.split("T")[1].replace(":00Z", ""); // Remove seconds and 'Z'

        String date = convertDate(match.getDate());
        if(match.getStatus().equals("LIVE") || match.getStatus().equals("FINISHED") || match.getStatus().equals("IN_PLAY") || match.getStatus().equals("PAUSED")){
            if(match.getStatus().equals("FINISHED")){
                holder.duration.setText("FT");
            } else {
                holder.duration.setText(date);
            }
        } else {
            if(match.getStatus().equals("SCHEDULED") || match.getStatus().equals("TIMED")){
                holder.duration.setText(date);
            } else {
                holder.duration.setText(match.getStatus());
            }
        }

        Picasso.get().load(match.getCupImage()).placeholder(R.drawable.ic_football).error(R.drawable.ic_football).into(holder.imageCup);
        holder.itemView.setOnClickListener(view -> {
            Intent fixturesIntent = new Intent(FixturesAdapter.this.context, MatchActivity.class);
            fixturesIntent.putExtra("id", match.getId());
            FixturesAdapter.this.context.startActivity(fixturesIntent);
            interstitialManager.showInterstitial(FixturesAdapter.this.activity);
        });

    }

    public String getOrdinalSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th"; // Special case for 11th, 12th, 13th
        }
        switch (day % 10) {
            case 1: return "st";
            case 2: return "nd";
            case 3: return "rd";
            default: return "th";
        }
    }


    private String convertDate(String utcDateString) {
        try {
            // Define the UTC date format (input string format)
            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // Ensure the date is parsed in UTC time zone

            // Parse the UTC date string into a Date object
            Date utcDate = utcFormat.parse(utcDateString);

            // Define the output format (including both date and time)
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
            outputFormat.setTimeZone(TimeZone.getDefault()); // Use the device's local time zone for display

            // Format the Date into the required format (date and time)
            String formattedDateTime = outputFormat.format(utcDate);
            return formattedDateTime;
        } catch (Exception e) {
            e.printStackTrace();
            return utcDateString;  // In case of an error, return the original string (or handle accordingly)
        }
    }


    public String formatDateWithSuffix(String dateTime) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM d", Locale.getDefault()); // e.g., "October 19"

        try {
            Date date = inputFormat.parse(dateTime);
            if (date != null) {
                String formattedDate = outputFormat.format(date);
                int day = Integer.parseInt(new SimpleDateFormat("d", Locale.getDefault()).format(date)); // Get the day
                String suffix = getOrdinalSuffix(day); // Get the suffix
                return formattedDate + suffix; // Concatenate the formatted date with the day and suffix
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception
        }
        return ""; // Return empty string if parsing fails
    }

    public int getItemCount() {
        return this.matchList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textHome;
        private final TextView textAway;
        private final TextView textResults;
        private final TextView duration;
        private final TextView date;
        private final ImageView imageCup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textHome = itemView.findViewById(R.id.textHome);
            this.textAway = itemView.findViewById(R.id.textAway);
            this.textResults = itemView.findViewById(R.id.textResults);
            this.imageCup = itemView.findViewById(R.id.imageCup);
            this.duration = itemView.findViewById(R.id.duration);
            this.date = itemView.findViewById(R.id.date);
        }
    }
}
