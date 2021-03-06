package com.vald3nir.mynanny.views.reports;

import android.graphics.Color;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.vald3nir.mynanny.R;
import com.vald3nir.mynanny.controllers.MQTTController;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class ReportsDelegate {

    void readData(ReportsActivity activity) {

        MQTTController controller = MQTTController.getInstance();

        controller.registerListener(new MQTTController.IMQTTController() {
            @Override
            public void successConnectionMQTT() {
            }

            @Override
            public void onMQTTException(Exception exception) {
                activity.showAlertNotification(exception.getMessage());
            }

            @Override
            public void responseMQTT(String topic, MqttMessage messageMQTT) {
                readJson(activity, new String(messageMQTT.getPayload()));
            }
        });
        controller.publish(activity.getString(R.string.topic_report_read), "");
        controller.subscribe(activity.getString(R.string.topic_report_response));
    }


    private void readJson(ReportsActivity activity, String json) {
        try {

            ArrayList<ChartItem> charts = new ArrayList<>();

            JSONObject jsonObject = new JSONObject(json);

            JSONArray temp_baby = jsonObject.getJSONArray("temp_baby");
            charts.add(new LineChartItem(generateChart(createFromJsonArray(temp_baby), activity.getString(R.string.baby_temperature)), activity));

            JSONArray temp_env = jsonObject.getJSONArray("temp_env");
            charts.add(new LineChartItem(generateChart(createFromJsonArray(temp_env), activity.getString(R.string.temperature)), activity));

            JSONArray light = jsonObject.getJSONArray("light"); // trocar por movimento
            charts.add(new LineChartItem(generateChart(createFromJsonArray(light), activity.getString(R.string.light)), activity));

            JSONArray humidity = jsonObject.getJSONArray("humidity");
            charts.add(new LineChartItem(generateChart(createFromJsonArray(humidity), activity.getString(R.string.humidity)), activity));

            activity.setCharts(charts);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private ArrayList<Entry> createFromJsonArray(JSONArray jsonArray) throws JSONException {
        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            values.add(new Entry(i, jsonArray.getJSONObject(i).getInt("value")));
        }
        return values;
    }

    private LineData generateChart(ArrayList<Entry> entries, String title) {
        LineDataSet dataSet = new LineDataSet(entries, title);
        dataSet.setLineWidth(5.0f);
        dataSet.setCircleRadius(5.0f);
        dataSet.setHighLightColor(Color.BLUE);
        dataSet.setDrawValues(false);
        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(dataSet);
        return new LineData(sets);
    }


}
