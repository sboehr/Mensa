package com.rac.simoneunddaniel.mensa.WeekSelection.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rac.simoneunddaniel.mensa.R;
import com.rac.simoneunddaniel.mensa.WeekSelection.DateHelper;
import com.rac.simoneunddaniel.mensa.WeekSelection.DayOfWeekSelectionActivity;

public class PastWeeksFragment extends Fragment {

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_past_weeks,
                container, false);

        listView = (ListView) view.findViewById(R.id.pastweekslist);

        DateHelper dateHelper = new DateHelper();

        String[] values = dateHelper.getWeeksOfYearAsStringArray();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


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
//                Toast.makeText(getActivity().getApplicationContext(),
//                        "Position :"+ position +"  ListItem : " +itemValue , Toast.LENGTH_LONG)
//                        .show();

                Intent intent = new Intent(getContext(), DayOfWeekSelectionActivity.class);
                intent.putExtra("date", itemValue);
                getActivity().startActivity(intent);
            }

        });

        return view;
    }


}
