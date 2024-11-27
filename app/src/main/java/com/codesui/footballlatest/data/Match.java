package com.codesui.footballlatest.data;
public class Match {

    private final String id;
    private final String homeTeam;
    private final String awayTeam;
    private final String cupImage;
    private String score;
    private final String date;
    private final String status;
    public Match(String id, String homeTeam, String awayTeam, String cupImage, String score, String date, String status) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.cupImage = cupImage;
        this.score = score;
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
    public String getCupImage() {
        return cupImage;
    }
    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;
    }
    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
}