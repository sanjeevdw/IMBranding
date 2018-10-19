package com.example.android.imbranding;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class HomepageLoader extends AsyncTaskLoader<List<HomepageState>> {

    private static final String LOG_TAG = HomepageLoader.class.getName();

    // Query URLs
    private String mUrlOne;

    public HomepageLoader(@NonNull Context context, String urlOne) {
        super(context);
        mUrlOne = urlOne;
        }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<HomepageState> loadInBackground() {
        if (mUrlOne == null) {
        return null;
        }
        List<HomepageState> homepageStateList = QueryUtils.fetchHomepageData(mUrlOne);
        return homepageStateList;
    }
}