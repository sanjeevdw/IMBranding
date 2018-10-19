package com.example.android.imbranding;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class HomepageActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<HomepageState>> {

    private static final String LOG_TAG = HomepageActivity.class.getSimpleName();
    private static final String STATE_REQUEST_URL = "http://139.59.33.52/imakeprofile/public/api/states/101?api_token=H2Y6wI6itQycNLPwoRI5BwrCBAodEFY7G21m3d9ueQW2zAWQGh8jYTQwAMcH43IVCgJKmrO8eVJmnzJNrc2guMYOZzOGlNHDFZ9k";
    private static final int HOMEPAGE_LOADER_ID = 2;
    private HomepageAdapter mAdapter;
    private CountryAdapter mCountryAdapter;
    private CityAdapter mCityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        TextView welcomeView = (TextView) findViewById(R.id.welcomeView);
        Intent userName = getIntent();
        Bundle bundle = userName.getExtras();

        if (bundle != null) {

            String userNameDisplay = (String) bundle.get("userName");
            welcomeView.setText(userNameDisplay);
        }
        TextView countryTextView = (TextView) findViewById(R.id.country_textView);
        TextView stateTextView = (TextView) findViewById(R.id.state_textView);

        setData();

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(HOMEPAGE_LOADER_ID, null, this);
        homepageNetworkRequest();
    }

    private void setData() {

       List<HomepageState> homepageStateList = new ArrayList<>();
        List<HomepageCountry> countryHomepageList = new ArrayList<>();
        List<HomepageCity> cityHomepageList = new ArrayList<>();

        mAdapter = new HomepageAdapter(this, android.R.layout.simple_spinner_dropdown_item, homepageStateList);
        Spinner stateSpinnerView = (Spinner) findViewById(R.id.state_spinner);
        stateSpinnerView.setAdapter(mAdapter);

        mCountryAdapter = new CountryAdapter(this, android.R.layout.simple_spinner_dropdown_item, countryHomepageList);
        Spinner countrySpinnerView = (Spinner) findViewById(R.id.country_spinner);
        countrySpinnerView.setAdapter(mCountryAdapter);

        mCityAdapter = new CityAdapter(this, android.R.layout.simple_spinner_dropdown_item, cityHomepageList);
        Spinner citySpinnerView = (Spinner) findViewById(R.id.city_spinner);
        citySpinnerView.setAdapter(mCityAdapter);
        }

    @NonNull
    @Override
    public Loader<List<HomepageState>> onCreateLoader(int i, @Nullable Bundle bundle) {

        return new HomepageLoader(this, STATE_REQUEST_URL);
        }

    @Override
    public void onLoadFinished(@NonNull Loader<List<HomepageState>> loader, List<HomepageState> homepageStateList) {

        if (homepageStateList !=null && !homepageStateList.isEmpty()) {
            mAdapter.addAll(homepageStateList);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<HomepageState>> loader) {

        mAdapter.clear();
    }

    private List<HomepageCountry> homepageNetworkRequest() {

        final List<HomepageCountry> countryHomepageList = new ArrayList<>();
        final List<HomepageCity> cityHomepageList = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://139.59.33.52/imakeprofile/public/api/countries?api_token=H2Y6wI6itQycNLPwoRI5BwrCBAodEFY7G21m3d9ueQW2zAWQGh8jYTQwAMcH43IVCgJKmrO8eVJmnzJNrc2guMYOZzOGlNHDFZ9k";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                        JSONObject baseJsonResponse = new JSONObject(response);

                        JSONArray countryArray = baseJsonResponse.getJSONArray("states");

                        for (int i = 0; i < countryArray.length(); i++) {

                            JSONObject currentState = countryArray.getJSONObject(i);
                            String countryList = currentState.getString("name");
                            HomepageCountry homepageCountry = new HomepageCountry(countryList);
                            countryHomepageList.add(homepageCountry);
                            mCountryAdapter.addAll(countryHomepageList);
                        }
                    } catch (JSONException e)  {
                            Log.e(LOG_TAG, e.getMessage());
                            Toast.makeText(getApplicationContext(), "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                        }
                        }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
                }

        });

        String urlTwo = "http://139.59.33.52/imakeprofile/public/api/cities/5?api_token=H2Y6wI6itQycNLPwoRI5BwrCBAodEFY7G21m3d9ueQW2zAWQGh8jYTQwAMcH43IVCgJKmrO8eVJmnzJNrc2guMYOZzOGlNHDFZ9k";
        StringRequest stringRequestTwo = new StringRequest(Request.Method.GET, urlTwo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject baseJsonResponse = new JSONObject(response);

                            JSONArray cityArray = baseJsonResponse.getJSONArray("cities");

                            for (int i = 0; i < cityArray.length(); i++) {

                                JSONObject currentCity = cityArray.getJSONObject(i);
                                String cityList = currentCity.getString("name");
                                HomepageCity homepageCity = new HomepageCity(cityList);
                                cityHomepageList.add(homepageCity);
                                mCityAdapter.addAll(cityHomepageList);
                            }
                        } catch (JSONException e)  {

                            Log.e(LOG_TAG, e.getMessage());
                            Toast.makeText(getApplicationContext(), "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
            }

        });
        queue.add(stringRequest);
        queue.add(stringRequestTwo);
        return countryHomepageList;
        }
}
