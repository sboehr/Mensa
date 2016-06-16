package com.rac.simoneunddaniel.mensa.RestServices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Daniel on 14.06.2016.
 */
public class AsyncTask extends android.os.AsyncTask<String, Void, String> {

    private String json;

    public AsyncResponse delegate = null;

    public AsyncTask(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            json = getHTML(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    protected void onPostExecute(String result) {
//        Globals globals = Globals.getInstance();
//        globals.setJson(json);
//
//        TextView tv = globals.getTextView();
//        JSONArray jArray = null;
//        try {
//            jArray = new JSONArray(json);
//            JSONObject jObject = jArray.getJSONObject(1);
//            tv.setText(jObject.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        delegate.processFinish(result);
    }


    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }


}
