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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.quakereport.QueryUtils.extractEarthquakes;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // read the JSON file and extract the values
        ArrayList<Events> earthquakes = QueryUtils.extractEarthquakes();

        // Create a fake list of earthquake locations.
//        ArrayList<Events> earthquakes = new ArrayList<>();
//        earthquakes.add(new Events("San Francisco", 7.20, 1493395019));
//        earthquakes.add(new Events("London", 6.1, 1493395019));
//        earthquakes.add(new Events("Tokyo", 3.9 ,1493395019));
//        earthquakes.add(new Events("Mexico City", 5.4 , 1493395019));
//        earthquakes.add(new Events("Moscow", 2.8 , 1493395019));
//        earthquakes.add(new Events("Rio de Janeiro", 4.9 , 1493395019));
//        earthquakes.add(new Events("Paris", 1.6 , 1493395019));


        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // ORIGINAL ADAPTER
        // Create a new {@link ArrayAdapter} of earthquakes
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(
         //       this, android.R.layout.simple_list_item_1, earthquakes);

        // CUSTOM ADAPTER
        EventsAdapter adapter = new EventsAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }
}
