package com.rac.simoneunddaniel.mensa.WeekSelection;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 * Created by Daniel on 16.06.2016.
 */
public class DateHelper {

    private String dateFormat = "dd.MM.yyyy";

    public ArrayList<String> getWeeksOfYear() {

        ArrayList<String> weeks = new ArrayList<>();

        //Setup Calendar

        Calendar cal = Calendar.getInstance();
        //get todays week of year
        cal.set(Calendar.HOUR_OF_DAY, 0);
        int currentWeekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
        //set current oldest date from database
        cal.set(2016, Calendar.MAY, 23);
        //get week of year to oldest date
        int weekofyear = cal.get(Calendar.WEEK_OF_YEAR) + 1;

        //Stringbuilder for concatination
        StringBuilder builder = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        for (int i = weekofyear; i <= currentWeekOfYear; i++) {
            //get start and end date of the week
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            String startDate = sdf.format(cal.getTime());
            cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
            String endDate = sdf.format(cal.getTime());

            //concatinate start and enddate
            builder.append(startDate).append(" - ").append(endDate);

            //add to list
            weeks.add(builder.toString());

            //clear stringbuilder
            builder.delete(0, builder.length());

            //go to next week of year
            cal.add(Calendar.WEEK_OF_YEAR, 1);
        }

        return weeks;
    }

    public String[] getWeeksOfYearAsStringArray() {
        ArrayList<String> weeksOfYear = getWeeksOfYear();
        //reverse weeksOfYear
        Collections.reverse(weeksOfYear);

        //create String[] for array adapter
        String[] weeksofYearAsStringArray = new String[weeksOfYear.size()];

        //paste values from list into string []
        for (int i = weeksOfYear.size() - 1; i >= 0; i--) {
            weeksofYearAsStringArray[i] = weeksOfYear.get(i);
        }

        return weeksofYearAsStringArray;
    }

    public String[] getDaysOfWeek(String date) {
        String[] days = new String[5];
        String[] dates = date.split(" - ");
        String[] dateElements = dates[0].split("\\.");

        //delete 0 at first character of day and month
        if (dateElements[0].startsWith("0")) {
            dateElements[0] = dateElements[0].substring(1);
        }
        if (dateElements[1].startsWith("0")) {
            dateElements[1] = dateElements[1].substring(1);
        }

        LocalDate dt = new LocalDate(Integer.parseInt(dateElements[2]), Integer.parseInt(dateElements[1]), Integer.parseInt(dateElements[0]));


        days[0] = "Montag, " + dt.withDayOfWeek(DateTimeConstants.MONDAY).toString(dateFormat);
        days[1] = "Dienstag, " + dt.withDayOfWeek(DateTimeConstants.TUESDAY).toString(dateFormat);
        days[2] = "Mittwoch, " + dt.withDayOfWeek(DateTimeConstants.WEDNESDAY).toString(dateFormat);
        days[3] = "Donnerstag, " + dt.withDayOfWeek(DateTimeConstants.THURSDAY).toString(dateFormat);
        days[4] = "Freitag, " + dt.withDayOfWeek(DateTimeConstants.FRIDAY).toString(dateFormat);

        return days;
    }

    public String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }
}
