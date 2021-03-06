
package com.vald3nir.mynanny.views.reports;

import android.os.Bundle;
import android.widget.ListView;

import com.vald3nir.mynanny.R;
import com.vald3nir.mynanny.views.custom.CustomActivity;

import java.util.ArrayList;

public class ReportsActivity extends CustomActivity {

    private ReportsDelegate delegate;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        delegate = new ReportsDelegate();

        initViews();
        readData();
    }

    private void readData() {
        delegate.readData(this);
    }

    public void setCharts(ArrayList<ChartItem> list) {
        listView.setAdapter(new ChartDataAdapter(getApplicationContext(), list));
    }

    private void initViews() {
        listView = findViewById(R.id.listView);
        findViewById(R.id.imv_back).setOnClickListener(v -> onBackPressed());
    }
}
