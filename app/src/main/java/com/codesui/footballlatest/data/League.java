package com.codesui.footballlatest.data;

public class League {
    private final String leagueName;
    private final String leagueArena;
    private final int leagueId;
    private final String code;
    private final String leagueImage;

    public League(String name, String arena, int id, String code, String leagueImage) {
        this.leagueName = name;
        this.leagueArena = arena;
        this.leagueId = id;
        this.code = code;
        this.leagueImage = leagueImage;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getLeagueArena() {
        return leagueArena;
    }

    public String getLeagueImage() {
        return leagueImage;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public String getCode() {
        return code;
    }
}
