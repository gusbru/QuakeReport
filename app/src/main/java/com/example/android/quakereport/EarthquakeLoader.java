package com.example.android.quakereport;

/**
 * Created by gusbru on 5/15/17.
 *
 * This class implements the AsyncTaskLoader to perform network
 * request in background
 *
 */

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;


public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Events>> {

    private String URL;

    // constructor
    public EarthquakeLoader(Context context, String URL) {
        super(context);
        this.URL = URL;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Events> loadInBackground() {
        if (URL == null) {
            return null;
        }

        String earthquakesJSON = QueryUtils.fetchData(URL);
        ArrayList<Events> earthquakes = QueryUtils.extractEarthquakes(earthquakesJSON);
        return earthquakes;
    }
}
