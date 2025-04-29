package com.codesui.footballlatest.data;
public class Match {

    private final String id;
    private final String homeTeam;
    private final String awayTeam;
    private final String homeImage;
    private final String awayImage;
    private final String cupImage;
    private String homeScore;
    private String awayScore;
    private final String date;
    private final String status;
    public Match(String id, String homeTeam, String awayTeam, String homeImage, String awayImage, String cupImage, String homeScore, String awayScore, String date, String status) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeImage = homeImage;
        this.awayImage = awayImage;
        this.cupImage = cupImage;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.date = date;
        this.status = status;
    }



    public  String getId(){return id;}
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
    public String getCupImage() {
        return cupImage;
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