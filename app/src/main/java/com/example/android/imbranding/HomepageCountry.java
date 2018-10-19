package com.example.android.imbranding;

import android.support.annotation.NonNull;

/**
 * {@link com.example.android.imbranding.HomepageCountry} to hold homepage country objects
 */
public class HomepageCountry {

    private String mCountryList;

    public HomepageCountry(String countryList) {

            mCountryList = countryList;
            }

        public String getCountryList() {
        return mCountryList;
        }


        @NonNull
        @Override
        public String toString() {
            return mCountryList;
        }
    }