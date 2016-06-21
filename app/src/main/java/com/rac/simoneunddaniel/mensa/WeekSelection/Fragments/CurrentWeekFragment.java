package com.rac.simoneunddaniel.mensa.WeekSelection.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.rac.simoneunddaniel.mensa.R;
import com.rac.simoneunddaniel.mensa.WeekSelection.DateHelper;

public class CurrentWeekFragment extends android.support.v4.app.Fragment {
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_week,
                container, false);

        listView = (ListView) view.findViewById(R.id.currentweekList);

        //try to get date from intent
        String date = getActivity().getIntent().getStringExtra("date");
        System.out.println(date);

        DateHelper dateHelper = new DateHelper();
        String[] values;

        if (date == null) {
            //when there is no date from intent use current date
            values = dateHelper.getDaysOfWeek(dateHelper.getCurrentDate());
        } else {
            String dateInterval[] = date.split(" - ");
            date = dateInterval[0];
            System.out.println(date);
            values = dateHelper.getDaysOfWeek(date);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.current_week_item, values) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Get the current item from ListView
                View view = super.getView(position, convertView, parent);

                // Get the Layout Parameters for ListView Current Item View
                ViewGroup.LayoutParams params = view.getLayoutParams();

                //get Toolbar height
                int toolbarheight = 0;
                ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
                if (bar != null) {
                    toolbarheight = bar.getHeight();
                }

                // Set the height of the Item View
                params.height = (listView.getHeight() - toolbarheight) / 5;
                view.setLayoutParams(params);

                return view;
            }
        };

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getActivity().getApplicationContext(),
                        "Position :" + position + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();
            }
        });

        return view;
    }
}
