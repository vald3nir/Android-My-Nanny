package com.vald3nir.mynanny.views.register;

import android.os.Bundle;
import android.widget.EditText;

import com.vald3nir.mynanny.R;
import com.vald3nir.mynanny.views.custom.CustomActivity;

import static com.vald3nir.mynanny.utils.AppUtils.getValue;

public class RegisterActivity extends CustomActivity {

    private EditText edtEmail, edtPassword, edtConfirmPassword;
    private RegisterDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        delegate = new RegisterDelegate(this);
        initViews();
    }

    private void initViews() {
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        edtConfirmPassword = findViewById(R.id.edt_confirm_password);
        findViewById(R.id.btn_register).setOnClickListener(v -> register());
    }

    private void register() {
        delegate.register(this, getValue(edtEmail), getValue(edtPassword), getValue(edtConfirmPassword));
    }
}

