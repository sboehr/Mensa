package com.rac.simoneunddaniel.mensa.WeekSelection;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by Daniel on 16.06.2016.
 */
public class DateHelper {

    public HashMap<String, String> getWeeksOfYear() {

        HashMap<String, String> weeks = new HashMap<String, String>();

        //Setup Calendar
        SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy");
        Calendar cal = Calendar.getInstance();
        //get todays week of year
        cal.set(Calendar.HOUR_OF_DAY, 0);
        int currentWeekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
        System.out.println(currentWeekOfYear);
        //set current oldest date from database
        cal.set(2016, Calendar.MAY, 23);
        //get week of year to oldest date
        int weekofyear = cal.get(Calendar.WEEK_OF_YEAR);

        for (int i = weekofyear; i <= currentWeekOfYear; i++) {
            //get start and end date of the week
            cal.set(Calendar.WEEK_OF_YEAR, i);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            String startDate = sdf.format(cal.getTime());
            cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
            String endDate = sdf.format(cal.getTime());

            //put start and end date in hashmap
            weeks.put(startDate, endDate);
        }

        return weeks;
    }
}
