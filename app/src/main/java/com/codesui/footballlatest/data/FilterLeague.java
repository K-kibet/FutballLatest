package com.codesui.footballlatest.data;

public class FilterLeague {
    public static final int ALL_LEAGUES_ID = -1;

    private final int id;
    private final String name;
    private final String logo;

    public FilterLeague(int id, String name, String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getLogo() { return logo; }
}
