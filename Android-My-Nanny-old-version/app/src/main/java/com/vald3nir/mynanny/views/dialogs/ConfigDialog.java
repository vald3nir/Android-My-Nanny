package com.vald3nir.mynanny.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.vald3nir.mynanny.R;

import static com.vald3nir.mynanny.utils.AppUtils.getURLStreaming;
import static com.vald3nir.mynanny.utils.AppUtils.getValue;
import static com.vald3nir.mynanny.utils.AppUtils.saveURLStreaming;

public class ConfigDialog extends Dialog {

    public ConfigDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        EditText ip_streaming = findViewById(R.id.edt_ip_streaming);
        ip_streaming.setText(getURLStreaming(getContext()));
        findViewById(R.id.btn_save).setOnClickListener(v -> {
            saveURLStreaming(getContext(), getValue(ip_streaming));
            dismiss();
        });
    }

}