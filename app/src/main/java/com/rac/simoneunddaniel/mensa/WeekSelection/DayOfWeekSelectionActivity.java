package com.rac.simoneunddaniel.mensa.WeekSelection;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.rac.simoneunddaniel.mensa.R;
import com.rac.simoneunddaniel.mensa.WeekSelection.Fragments.CurrentWeekFragment;

public class DayOfWeekSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_of_week_selection);

        //Setup Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Dayselection");
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);



        FragmentManager fragMan = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();
        Fragment currentWeek = new CurrentWeekFragment();
        fragTransaction.add(R.id.fragment_container, currentWeek, "HELLO");
        fragTransaction.commit();
    }
}

