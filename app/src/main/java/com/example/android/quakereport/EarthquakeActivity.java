/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.quakereport.QueryUtils.extractEarthquakes;

public class EarthquakeActivity extends AppCompatActivity implements LoaderCallbacks<ArrayList<Events>>{

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    //    public static final String URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    public static final String URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";
//    public static final String URL = "fdsfaf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        // read the JSON file and extract the values
//        final ArrayList<Events> earthquakes = QueryUtils.extractEarthquakes();

        // get the information from network using AsyncTask
        //RequestFromServer task = new RequestFromServer();
        //task.execute(URL);

        // get the information from network using Loader
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(0, null, this);


    }

    @Override
    public Loader<ArrayList<Events>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(EarthquakeActivity.this, URL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Events>> loader, ArrayList<Events> data) {
        updateIU(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Events>> loader) {

    }

    /**
     *
     * @param eventses
     */
    private void updateIU(ArrayList<Events> eventses) {
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // ORIGINAL ADAPTER
        // Create a new {@link ArrayAdapter} of earthquakes
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(
        //       this, android.R.layout.simple_list_item_1, earthquakes);

        // CUSTOM ADAPTER
        final EventsAdapter adapter = new EventsAdapter(EarthquakeActivity.this, eventses);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);


        // set action on click - Open the browser with the event site
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Way #1 to get the url
//                String url = earthquakes.get(position).getmUrl();

                // Way #2 to get the url
                Events currentEvent = adapter.getItem(position);
                String url = currentEvent.getmUrl();

                // call the method to open the url with intent
                openWebPage(url);
            }
        });
    }

    /**
     * This method start an activity (web browser) to show more information about
     * the earthquake.
     *
     * @param url the website address with more information about the earthquake
     */
    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private class RequestFromServer extends AsyncTask<String, Void, ArrayList<Events>> {
        @Override
        protected ArrayList<Events> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            String earthquakesJSON = QueryUtils.fetchData(urls[0]);
            ArrayList<Events> earthquakes = QueryUtils.extractEarthquakes(earthquakesJSON);

            return earthquakes;
        }

        @Override
        protected void onPostExecute(ArrayList<Events> eventses) {

            updateIU(eventses);

        }
    }



}
