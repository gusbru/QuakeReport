package com.example.android.quakereport;

/**
 * Created by gusbru on 4/28/17.
 *
 * This class register the earthquake events
 *
 */

public class Events {

    private String mPlace;
    private double mMagnitude;
    private long mDate;
    private String mUrl;

    public Events(String mPlace, double mMagnitude, long mDate, String mUrl) {
        this.mMagnitude = mMagnitude;
        this.mPlace = mPlace;
        this.mDate = mDate;
        this.mUrl = mUrl;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmPlace() {
        return mPlace;
    }

    public long getmDate() {
        return mDate;
    }

    public String getmUrl() {
        return mUrl;
    }
}
