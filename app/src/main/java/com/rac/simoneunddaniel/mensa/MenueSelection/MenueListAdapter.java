package com.rac.simoneunddaniel.mensa.MenueSelection;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.rac.simoneunddaniel.mensa.R;

/**
 * Created by Daniel on 23.06.2016.
 */
public class MenueListAdapter extends BaseAdapter {

    Context context;
    String[] data;
    private static LayoutInflater inflater = null;

    public MenueListAdapter(Context context, String[] data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        try {
            if (vi == null)
                vi = inflater.inflate(R.layout.menue_item, null);
            String menueFragments[] = data[position].split("%&%");

            TextView header = (TextView) vi.findViewById(R.id.header);
            header.setText(menueFragments[0].split("%§%")[1]);
            header.setGravity(Gravity.CENTER_HORIZONTAL);
            TextView datatv = (TextView) vi.findViewById(R.id.data);
            datatv.setText(menueFragments[1]);
            datatv.setGravity(Gravity.CENTER_VERTICAL);
        } catch (Exception e) {
            Toast.makeText(context, "Menü nicht gefunden", Toast.LENGTH_SHORT).show();
        }

        // Get the Layout Parameters for ListView Current Item View
        ViewGroup.LayoutParams params = parent.getLayoutParams();

        // Set the height of the Item View
        params.height = (parent.getHeight() - 20) / 4;
        vi.setLayoutParams(params);


        return vi;
    }
}