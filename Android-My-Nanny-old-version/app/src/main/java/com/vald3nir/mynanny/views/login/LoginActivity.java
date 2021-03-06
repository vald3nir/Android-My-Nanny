package com.vald3nir.mynanny.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.vald3nir.mynanny.R;
import com.vald3nir.mynanny.views.MainActivity;
import com.vald3nir.mynanny.views.custom.CustomActivity;
import com.vald3nir.mynanny.views.register.RegisterActivity;

import static com.vald3nir.mynanny.utils.AppUtils.getValue;


public class LoginActivity extends CustomActivity {

    private LoginDelegate delegate;
    private EditText edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        delegate = new LoginDelegate(this);
        initViews();
    }

    @Override
    public void onBackPressed() {
    }

    private void initViews() {
        this.edtEmail = findViewById(R.id.edt_email);
        this.edtPassword = findViewById(R.id.edt_password);

        findViewById(R.id.btn_login).setOnClickListener(v -> login());
        findViewById(R.id.btn_register).setOnClickListener(v -> callRegister());
    }

    private void login() {
        delegate.login(this, getValue(edtEmail), getValue(edtPassword));
    }

    public void callMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void callRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}