package com.vald3nir.mynanny.views.splash;

import android.content.Intent;
import android.os.Bundle;

import com.vald3nir.mynanny.R;
import com.vald3nir.mynanny.views.custom.CustomActivity;
import com.vald3nir.mynanny.views.login.LoginActivity;

public class SplashActivity extends CustomActivity {

    private SplashDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        delegate = new SplashDelegate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        delegate.connectMQTT(this);
    }

    public void callLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

}
