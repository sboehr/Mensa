package com.rac.simoneunddaniel.mensa.RestServices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Daniel on 14.06.2016.
 */
public class AsyncTask extends android.os.AsyncTask<String, Void, String> {

    public static String ip = "192.168.0.236";

    private String json;

    public AsyncResponse delegate = null;

    public AsyncTask(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            //TODO: remove Thread.sleep()
            Thread.sleep(5000);
            json = getHTML(params[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    protected void onPostExecute(String result) {
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
