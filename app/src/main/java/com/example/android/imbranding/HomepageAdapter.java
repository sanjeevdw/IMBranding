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

public class HomepageAdapter extends ArrayAdapter<HomepageState> {

    private final int mResource;

    public HomepageAdapter(@NonNull Context context, int resource, List<HomepageState> homepageStateList) {
        super(context, resource, 0, homepageStateList);
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {

        View spinnerItemView = convertView;

        if (spinnerItemView == null) {
        spinnerItemView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);

        }

        HomepageState currentHomepageState = getItem(position);

        TextView stateView = (TextView) spinnerItemView.findViewById(R.id.spinner_text_item_view);
        stateView.setText(currentHomepageState.getStateList());

        return spinnerItemView;
        }

        @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
