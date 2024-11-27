package com.codesui.footballlatest.data;

public class Team {
    String name;
    String image;
    int id;
    private boolean isFavorite;

    public Team(String name, String image, int id, boolean isFavorite) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.isFavorite = isFavorite;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }
    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
}
