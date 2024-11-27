package com.codesui.footballlatest.Adapter;

import android.content.Context;

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

    public CompetitionAdapter(Context ctx, FragmentManager fm) {
        super(fm);
        context = ctx;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new MatchesFragment();
            case 2:
                return new TeamsFragment();
            case 3:
                return new TopscorersFragment();
            default:
                return new StandingsFragment();
        }
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