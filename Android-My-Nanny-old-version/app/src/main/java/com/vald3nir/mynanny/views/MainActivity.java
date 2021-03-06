package com.vald3nir.mynanny.views;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.vald3nir.mynanny.R;
import com.vald3nir.mynanny.views.dialogs.ConfigDialog;
import com.vald3nir.mynanny.views.monitor.MonitorFragment;
import com.vald3nir.mynanny.views.player.PlayerFragment;
import com.vald3nir.mynanny.views.reports.ReportsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_player, new PlayerFragment())
//                .replace(R.id.container_player, new PlayerFragmentDemo())
                .replace(R.id.container_info, new MonitorFragment())
                .commit();

        findViewById(R.id.txt_title_app).setOnClickListener(v -> openConfigView());
        findViewById(R.id.btn_reports).setOnClickListener(v -> openReportsActivity());
    }

    @Override
    public void onBackPressed() {
    }

    private void openConfigView() {
        new ConfigDialog(this).show();
    }

    private void openReportsActivity() {
        startActivity(new Intent(this, ReportsActivity.class));
    }

}
