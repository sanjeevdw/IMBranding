package com.example.android.imbranding;

import android.support.annotation.NonNull;

/**
 * {@link HomepageState} to hold homepage state objects
 */

public class HomepageState {
    private String mStateList;
    public HomepageState(String stateList) {

        mStateList = stateList;
        }

        public String getStateList() {
        return mStateList;
    }

    @NonNull
    @Override
    public String toString() {
        return mStateList;
        }
}