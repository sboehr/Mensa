package com.rac.simoneunddaniel.mensa.MenueSelection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.glomadrian.roadrunner.IndeterminateRoadRunner;
import com.rac.simoneunddaniel.mensa.R;
import com.rac.simoneunddaniel.mensa.RestServices.AsyncResponse;
import com.rac.simoneunddaniel.mensa.RestServices.AsyncTask;
import com.rac.simoneunddaniel.mensa.ReviewSelection.ReviewSelectionActivity;

public class MenueSelectionActivity extends AppCompatActivity {

    private ListView listView;
    private IndeterminateRoadRunner spinner;
    private String[] values, e_ids;

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
        spinner = (com.github.glomadrian.roadrunner.IndeterminateRoadRunner) findViewById(R.id.spinner);

        listView = (ListView) findViewById(R.id.listViewMenueSelection);

        String date = getIntent().getStringExtra("date");

        AsyncTask task = new AsyncTask(new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                MenueHelper helper = new MenueHelper();

                values = helper.getMenuesOfTheDayAsStringArray(output);
                e_ids = new String[values.length];
                for (int i = 0; i < values.length; i++) {
                    e_ids[i] = values[i].split("%§%")[0];
                }
                listView.setAdapter(new MenueListAdapter(getApplication(), values));
                spinner.setVisibility(View.INVISIBLE);

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
                Intent intent = new Intent(getApplicationContext(), ReviewSelectionActivity.class);
                intent.putExtra("e_ID", e_ids[position]);
                intent.putExtra("menue", values[position]);
                startActivity(intent);

            }
        });

    }
}
