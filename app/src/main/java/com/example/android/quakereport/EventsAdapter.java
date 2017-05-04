package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


/**
 * Created by gusbru on 4/28/17.
 */

public class EventsAdapter extends ArrayAdapter<Events> {

    /**
     *
     * @param context activity context
     * @param event ArrayList element with the earthquake event
     */
    public EventsAdapter(Activity context, ArrayList<Events> event) {
        //
        super(context, 0, event);
    }


    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return the populated listItemView
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.events_view,
                    parent,
                    false
            );
        }

        // Get the {@link Events} object located at this position in the list
        Events currentEvent = getItem(position);


        // Start to fill the elements at events_view
        // ==========================================

        // magnitude
        TextView magnitude = (TextView) listItemView.findViewById(R.id.events_magnitude);
        magnitude.setText(String.format(Locale.US, "%2.1f", currentEvent.getmMagnitude()));

        // Format the circle with the correct color for each magnitude value
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEvent.getmMagnitude());

        // set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        // location and distance
        TextView distance = (TextView) listItemView.findViewById(R.id.events_distance);
        TextView location = (TextView) listItemView.findViewById(R.id.events_location);

        String separator = "of";
        String infoLocationAndDistance = currentEvent.getmPlace();
        if (infoLocationAndDistance.contains(separator)) {
            int start = infoLocationAndDistance.indexOf(separator) + separator.length();
            int end   = infoLocationAndDistance.length();
            distance.setText(infoLocationAndDistance.substring(0, start));
            location.setText(infoLocationAndDistance.substring(start + 1, end));
        } else {
            distance.setText("Near of");
            location.setText(infoLocationAndDistance);
        }


        // date and time in Unix-time
        Date mDate = new Date(currentEvent.getmDate());

        // convert date to human readable
        TextView date = (TextView) listItemView.findViewById(R.id.events_date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD, yyyy", Locale.US);
        String dateToDisplay = dateFormat.format(mDate);
        date.setText(dateToDisplay);


        // convert time to human readable
        TextView time = (TextView) listItemView.findViewById(R.id.events_time);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm a", Locale.US);
        String timeToDisplay = timeFormat.format(mDate);
        time.setText(timeToDisplay);

        return listItemView;
    }

    private int getMagnitudeColor(double mag) {
        int magnitudeRange = (int) Math.floor(mag);
        int magnitudeColor;

        switch (magnitudeRange) {
            case 1:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 2:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 3:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 4:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 5:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 6:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 7:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 8:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 9:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            default:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
        }

        return  magnitudeColor;
    }

}
