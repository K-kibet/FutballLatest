package com.codesui.footballlatest.data;

public class HomeLeague {
    private final String leagueName;
    private final String leagueId;
    private final String leagueImage;

    private boolean active;

    public HomeLeague(String leagueName, String leagueId, String leagueImage, Boolean active) {
        this.leagueName = leagueName;
        this.leagueId = leagueId;
        this.leagueImage = leagueImage;
        this.active = active;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public String getLeagueImage() {
        return leagueImage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
