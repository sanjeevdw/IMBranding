package com.example.android.imbranding;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CityAdapter extends ArrayAdapter<HomepageCity>  {

    private final int mResource;

    public CityAdapter(@NonNull Context context, int resource, List<HomepageCity> cityList) {
        super(context, resource, 0, cityList);
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {

        View spinnerItemView = convertView;

        if (spinnerItemView == null) {
            spinnerItemView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);

        }

        HomepageCity currentHomepageCity = getItem(position);

        TextView cityView = (TextView) spinnerItemView.findViewById(R.id.spinner_text_item_view);
        cityView.setText(currentHomepageCity.getCityList());

        return spinnerItemView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
