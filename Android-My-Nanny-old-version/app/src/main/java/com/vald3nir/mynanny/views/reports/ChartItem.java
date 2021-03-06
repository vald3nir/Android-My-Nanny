package com.vald3nir.mynanny.views.reports;

import android.content.Context;
import android.view.View;

import com.github.mikephil.charting.data.ChartData;

public abstract class ChartItem {

    static final int TYPE_LINE_CHART = 1;

    final ChartData<?> mChartData;

    ChartItem(ChartData<?> cd) {
        this.mChartData = cd;
    }

    public abstract int getItemType();

    public abstract View getView(View convertView, Context c);
}
