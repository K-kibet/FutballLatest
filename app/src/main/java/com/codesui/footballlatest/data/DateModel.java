package com.codesui.footballlatest.data;

import java.util.Date;

public class DateModel {
    private String dayNumber;
    private String dayName;
    private Date fullDate;

    public DateModel(String dayNumber, String dayName, Date fullDate) {
        this.dayNumber = dayNumber;
        this.dayName = dayName;
        this.fullDate = fullDate;
    }

    public String getDayNumber() {
        return dayNumber;
    }

    public String getDayName() {
        return dayName;
    }

    public Date getFullDate() {
        return fullDate;
    }
}
