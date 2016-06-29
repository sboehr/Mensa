package com.rac.simoneunddaniel.mensa.MenueSelection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Daniel on 23.06.2016.
 */
public class MenueHelper {

    public String[] getMenuesOfTheDayAsStringArray(String json) {

        String[] menuesAsStringArray = new String[4];
        try {
            JSONArray menuesOfTheDay = new JSONArray(json);

            for(int i = 0; i < menuesOfTheDay.length(); i++){
                JSONObject menue = menuesOfTheDay.getJSONObject(i);
                String title = menue.getString("title");
                String description = menue.getString("description");
                menuesAsStringArray[i] = title + ":%&%" + description;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return menuesAsStringArray;
    }
}
