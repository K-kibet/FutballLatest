package com.codesui.footballlatest.Adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.codesui.footballlatest.R;
import com.codesui.footballlatest.competition.MatchesFragment;
import com.codesui.footballlatest.competition.StandingsFragment;
import com.codesui.footballlatest.competition.TeamsFragment;
import com.codesui.footballlatest.competition.TopscorersFragment;

public class CompetitionAdapter extends FragmentStatePagerAdapter {

    private static final int TABS = 4;
    private final Context context;
    private final int competitionId;
    private final String competitionCode;

    public CompetitionAdapter(Context ctx, FragmentManager fm, int competitionId, String competitionCode) {
        super(fm);
        context = ctx;
        this.competitionId = competitionId;
        this.competitionCode = competitionCode;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position) {
            case 1:
                fragment = new MatchesFragment();
                break;
            case 2:
                fragment = new TeamsFragment();
                break;
            case 3:
                fragment = new TopscorersFragment();
                break;
            default:
                fragment = new StandingsFragment();
        }

        // Set arguments
        Bundle args = new Bundle();
        args.putInt("competitionId", competitionId);
        args.putString("competitionCode", competitionCode);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 1:
                return context.getString(R.string.category_matches);
            case 2:
                return context.getString(R.string.category_teams);
            case 3:
                return context.getString(R.string.category_topscorers);
            default:
                return context.getString(R.string.category_standings);
        }
    }

    @Override
    public int getCount() {
        return TABS;
    }
}