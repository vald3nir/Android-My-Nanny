package com.vald3nir.mynanny.views.reports;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class ChartDataAdapter extends ArrayAdapter<ChartItem> {

    public ChartDataAdapter(Context context, List<ChartItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return getItem(position).getView(convertView, getContext());
    }

    @Override
    public int getItemViewType(int position) {
        ChartItem ci = getItem(position);
        return ci != null ? ci.getItemType() : 0;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }
}