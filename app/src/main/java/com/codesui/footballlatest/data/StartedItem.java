package com.codesui.footballlatest.data;

public class StartedItem {
        private int imageResId;
        private String title;
        private OnItemAction action;

        public StartedItem(int imageResId, String title, OnItemAction action) {
            this.imageResId = imageResId;
            this.title = title;
            this.action = action;
        }

        public int getImageResId() { return imageResId; }
        public String getTitle() { return title; }
        public OnItemAction getAction() { return action; }
    }
