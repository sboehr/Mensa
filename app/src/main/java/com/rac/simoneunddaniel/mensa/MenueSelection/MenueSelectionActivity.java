package com.rac.simoneunddaniel.mensa.MenueSelection;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rac.simoneunddaniel.mensa.R;
import com.rac.simoneunddaniel.mensa.RestServices.AsyncResponse;
import com.rac.simoneunddaniel.mensa.RestServices.AsyncTask;
//import com.rac.simoneunddaniel.mensa.RoadRunner.RoadRunner;

public class MenueSelectionActivity extends AppCompatActivity {

    private ListView listView;
//    private com.github.glomadrian.roadrunner.IndeterminateRoadRunner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue_selection);

        //Setup Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("MenueSelection");
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //Setup spinner
//        spinner = (com.github.glomadrian.roadrunner.IndeterminateRoadRunner) findViewById(R.id.spinner);

        listView = (ListView) findViewById(R.id.listViewMenueSelection);

        String date = getIntent().getStringExtra("date");

        AsyncTask task = new AsyncTask(new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                MenueHelper helper = new MenueHelper();

                String[] values = helper.getMenuesOfTheDayAsStringArray(output);
                listView.setAdapter(new MenueListAdapter(getApplication(), values));
//                spinner.setVisibility(View.INVISIBLE);
            }
        });

        task.execute("http://" + AsyncTask.ip + ":8080/mensa-platform-rest/resources/MenueService/getMenuesOfTheDay?date=" + date.split(", ")[1]);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

            }
        });

    }
}
