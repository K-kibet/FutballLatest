package com.codesui.footballlatest.data;

public class Standing {
    private final String position;
    private final String image;
    private final String name;
    private final String played;
    private final String won;
    private final String draw;
    private final String lost;
    private final String difference;
    private final String points;

    public Standing(String position, String image, String name, String played, String won, String draw, String lost, String difference, String points) {
        this.position = position;
        this.image = image;
        this.name = name;
        this.played = played;
        this.won = won;
        this.draw = draw;
        this.lost = lost;
        this.difference = difference;
        this.points = points;
    }

    public String getPosition() {
        return position;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPlayed() {
        return played;
    }

    public String getWon() {
        return won;
    }

    public String getDraw() {
        return draw;
    }

    public String getLost() {
        return lost;
    }

    public String getDifference() {
        return difference;
    }

    public String getPoints() {
        return points;
    }
}
