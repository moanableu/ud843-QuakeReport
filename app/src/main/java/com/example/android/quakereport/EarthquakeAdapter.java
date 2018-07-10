package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    public EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View eView = convertView;
        if (eView == null){
            eView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent,false);
        }

        Earthquake currentEarthquake = getItem(position);
        TextView magnitude = eView.findViewById(R.id.magnitude);
        TextView city = eView.findViewById(R.id.city);
        TextView date = eView.findViewById(R.id.date);

        return eView;
    }
}
