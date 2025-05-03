package com.codesui.footballlatest.data;

import java.util.Date;

public class DateModel {
    private final String dayNumber;
    private final String dayName;
    private final Date fullDate;

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
