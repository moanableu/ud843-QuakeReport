package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter <Earthquake> {
    private TextView magnitude, date, hour, mainLocation, offsetLocation;
    private String formattedDate, formattedTime, originalLocation, primaryLocation, locationOffset, formattedMagnitude;

    // set the params that will allow to split text via the literal ' of '
    private static final String LOCATION_SEPARATOR = " of ";

    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    public EarthquakeAdapter(Context context, ArrayList <Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View eView = convertView;
        if (eView == null) {
            eView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        //following section set magnitude view to use one decimal space only
        magnitude = eView.findViewById(R.id.magnitude);
        //magnitude.setText(currentEarthquake.getMagnitude());

        // Format magnitude using helper method
        formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        magnitude.setText(formattedMagnitude);

        //set background drawable for magnitude
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        // following section is for string manipulation that will split location information based on a literal
        originalLocation = currentEarthquake.getLocation();

        // split based on literal constant
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        //create widgets
        // display main location
        mainLocation = eView.findViewById(R.id.primary_location);
        mainLocation.setText(primaryLocation);

        // display offset location
        offsetLocation = eView.findViewById(R.id.location_offset);
        offsetLocation.setText(locationOffset);

        // following section is for time formats
        // create new date obj from time in JSON array
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());
        date = eView.findViewById(R.id.date);

        //create widgets
        // format day using helper method
        formattedDate = formatDate(dateObject);
        date.setText(formattedDate);
        //format hour using helper method
        hour = eView.findViewById(R.id.time);
        formattedTime = formatTime(dateObject);
        hour.setText(formattedTime);

        return eView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeValue = (int) Math.floor(magnitude);
        switch (magnitudeValue){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    // magnitude trimmed to one decimal space
    private String formatMagnitude (double magnitude){
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    // readable day dateTime
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormat.format(dateObject);
    }

    //readable time format
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        return timeFormat.format(dateObject);
    }
}
