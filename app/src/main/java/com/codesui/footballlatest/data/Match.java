package com.codesui.footballlatest.data;

public class Match {

    private final String id;
    private int leagueId;
    private String leagueCode;
    private String leagueName;
    private String leagueLogo;
    private final String homeTeam;
    private final String awayTeam;
    private final String homeImage;
    private final String awayImage;
    private String homeScore;
    private String awayScore;
    private final String date;
    private final String status;

    public Match(String id, int leagueId, String leagueCode, String leagueName, String leagueLogo, String homeTeam, String awayTeam, String homeImage, String awayImage, String homeScore, String awayScore, String date, String status) {
        this.id = id;
        this.leagueId = leagueId;
        this.leagueCode = leagueCode;
        this.leagueName = leagueName;
        this.leagueLogo = leagueLogo;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeImage = homeImage;
        this.awayImage = awayImage;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.date = date;
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public String getLeagueCode() {
        return leagueCode;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getLeagueLogo() {
        return leagueLogo;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getHomeImage() {
        return homeImage;
    }

    public String getAwayImage() {
        return awayImage;
    }

    public String getHomeScore() {
        return homeScore;
    }

    public String getAwayScore() {
        return awayScore;
    }

    public void setHomeScore(String homeScore) {
        this.homeScore = homeScore;
    }

    public void setAwayScore(String awayScore) {
        this.awayScore = awayScore;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
}