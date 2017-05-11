package com.example.android.quakereport;

/**
 * Created by gusbru on 4/28/17.
 * <p>
 * This class register the earthquake events
 */

public class Events {

    private String mPlace;
    private double mMagnitude;
    private long mDate;
    private String mUrl;

    /**
     * Construct a new {@link Events} object
     *
     * @param mPlace where the earthquake occurred
     * @param mMagnitude the intensity of the earthquake
     * @param mDate when the earthquake happened
     * @param mUrl webpage address with more information about the earthquake
     */
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
