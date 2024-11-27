package com.codesui.footballlatest.data;

public class Player {
    String id;
    String name;
    String goals;
    String teamImage;


    public Player(String id, String name, String goals, String teamImage) {
        this.id = id;
        this.name = name;
        this.goals = goals;
        this.teamImage = teamImage;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGoals() {
        return goals;
    }

    public String getTeamImage() {
        return teamImage;
    }
}
