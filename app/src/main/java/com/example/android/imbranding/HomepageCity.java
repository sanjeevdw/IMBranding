package com.example.android.imbranding;

import android.support.annotation.NonNull;

/**
 * {@link com.example.android.imbranding.HomepageCity} to hold homepage city objects
 */
public class HomepageCity {

    private String mCityList;

    public HomepageCity(String cityList) {

        mCityList = cityList;
    }

    public String getCityList() {
        return mCityList;
    }


    @NonNull
    @Override
    public String toString() {
        return mCityList;
    }
}
