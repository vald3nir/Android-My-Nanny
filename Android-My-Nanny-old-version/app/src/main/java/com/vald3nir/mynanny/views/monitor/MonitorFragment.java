package com.vald3nir.mynanny.views.monitor;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.vald3nir.mynanny.R;
import com.vald3nir.mynanny.views.custom.CustomFragment;

public class MonitorFragment extends CustomFragment {

    private MonitorDelegate delegate;
    private TextView txtBabyTemperature, txtTemperature, txtHumidity;
    private AlertDialog alertDialog;
    private ImageView lightIcon;
    private Switch changeLightSwitch;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate = new MonitorDelegate(this);
    }

    public void setValueHumidity(String humidityValue) {
        txtHumidity.setText(humidityValue);
    }

    public void setValueBabyTemperature(String lightValue) {
        txtBabyTemperature.setText(lightValue);
    }

    public void setValueTemperature(String temperatureValue) {
        txtTemperature.setText(temperatureValue);
    }

    public void setValueLight(boolean status) {
        changeLightSwitch.setChecked(status);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitor, container, false);

        txtBabyTemperature = view.findViewById(R.id.txt_baby_temperature);
        txtTemperature = view.findViewById(R.id.txt_temperature);
        txtHumidity = view.findViewById(R.id.txt_humidity);

        lightIcon = view.findViewById(R.id.light_icon);
        changeLightSwitch = view.findViewById(R.id.change_light_switch);
        changeLightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeLightValue(isChecked);
            }
        });

        return view;
    }

    private void changeLightValue(boolean isChecked) {
        lightIcon.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        delegate.publishValueLight(this, isChecked ? "1" : "0");
    }

    @Override
    public void onResume() {
        super.onResume();
        delegate.subscribeTopics(this);
    }

    public void showAlertDialog(int icon, int message) {
        if (alertDialog != null) alertDialog.dismiss();
        alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle(getString(R.string.alert));
        alertDialog.setIcon(icon);
        alertDialog.setMessage(getString(message));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.ok), (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }


}
