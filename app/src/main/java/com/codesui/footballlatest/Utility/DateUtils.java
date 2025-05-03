package com.codesui.footballlatest.Utility;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String convertUtcToLocalTime(String utcDate) {
        // Parse the UTC date string to an Instant
        Instant instant = Instant.parse(utcDate);

        // Convert to system default timezone
        ZonedDateTime localDateTime = instant.atZone(ZoneId.systemDefault());

        // Format to hh:mm
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return formatter.format(localDateTime);
    }

    public static String convertDate(String utcDateString) {
        try {
            /*// Define the UTC date format (input string format)
            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // Ensure the date is parsed in UTC time zone

            // Parse the UTC date string into a Date object
            Date utcDate = utcFormat.parse(utcDateString);

            // Define the output format (including both date and time)
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
            outputFormat.setTimeZone(TimeZone.getDefault()); // Use the device's local time zone for display

            // Format the Date into the required format (date and time)
            String formattedDateTime = outputFormat.format(utcDate);
            return formattedDateTime;*/


            // Parse the UTC date string to an Instant
            Instant instant = Instant.parse(utcDateString);

            // Convert to system default timezone
            ZonedDateTime localDateTime = instant.atZone(ZoneId.systemDefault());

            // Format to hh:mm
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            return formatter.format(localDateTime);
        } catch (Exception e) {
            e.printStackTrace();
            return utcDateString;  // In case of an error, return the original string (or handle accordingly)
        }
    }
}

/*public class DateUtils {
    public static String convertUtcToLocalTime(String utcDate) {
        try {
            // Input format from API (UTC)
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));  // Input is UTC

            // Output format (local time)
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
            outputFormat.setTimeZone(TimeZone.getDefault());       // Convert to local time

            Date date = inputFormat.parse(utcDate);
            return outputFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    private String convertDate(String utcDateString) {
        try {
            // Define the UTC date format (input string format)
            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // Ensure the date is parsed in UTC time zone

            // Parse the UTC date string into a Date object
            Date utcDate = utcFormat.parse(utcDateString);

            // Define the output format (including both date and time)
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
            outputFormat.setTimeZone(TimeZone.getDefault()); // Use the device's local time zone for display

            // Format the Date into the required format (date and time)
            String formattedDateTime = outputFormat.format(utcDate);
            return formattedDateTime;
        } catch (Exception e) {
            e.printStackTrace();
            return utcDateString;  // In case of an error, return the original string (or handle accordingly)
        }
    }


    public String formatDateWithSuffix(String dateTime) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM d", Locale.getDefault()); // e.g., "October 19"

        try {
            Date date = inputFormat.parse(dateTime);
            if (date != null) {
                String formattedDate = outputFormat.format(date);
                int day = Integer.parseInt(new SimpleDateFormat("d", Locale.getDefault()).format(date)); // Get the day
                String suffix = getOrdinalSuffix(day); // Get the suffix
                return formattedDate + suffix; // Concatenate the formatted date with the day and suffix
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception
        }
        return ""; // Return empty string if parsing fails
    }

    public String getOrdinalSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th"; // Special case for 11th, 12th, 13th
        }
        switch (day % 10) {
            case 1: return "st";
            case 2: return "nd";
            case 3: return "rd";
            default: return "th";
        }
    }
}*/


