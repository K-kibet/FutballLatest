package com.codesui.footballlatest.data;

public class StartedItem {
    private final int imageResId;
    private final String title;
    private final OnItemAction action;

    public StartedItem(int imageResId, String title, OnItemAction action) {
        this.imageResId = imageResId;
        this.title = title;
        this.action = action;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public OnItemAction getAction() {
        return action;
    }
}
