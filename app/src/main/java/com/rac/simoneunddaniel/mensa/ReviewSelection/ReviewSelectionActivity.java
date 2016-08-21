package com.rac.simoneunddaniel.mensa.ReviewSelection;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.rac.simoneunddaniel.mensa.R;
import com.rac.simoneunddaniel.mensa.RestServices.AsyncResponse;
import com.rac.simoneunddaniel.mensa.RestServices.AsyncTask;

public class ReviewSelectionActivity extends AppCompatActivity {

    private TextView tv;
    private String e_id, menue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tv = (TextView) findViewById(R.id.tvReview);

        e_id = getIntent().getStringExtra("e_ID");
        menue = getIntent().getStringExtra("menue");
        tv.setText(e_id + "\n" + menue);

        AsyncTask asyncTask = new AsyncTask(new AsyncResponse() {
            @Override
            public void processFinish(String output) {
               tv.setText(e_id + "\n" + menue + "\n\n" + output);
            }
        });

        String url = "http://" + AsyncTask.ip + ":8080/mensa-platform-rest/resources/ReviewService/getReviews?id=" + e_id;

        asyncTask.execute(url);


    }

}
