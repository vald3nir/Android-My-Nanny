package com.vald3nir.mynanny.views.player;

import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vald3nir.mynanny.R;
import com.vald3nir.mynanny.views.custom.CustomFragment;

public class PlayerFragmentDemo extends CustomFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_player_demo, container, false);
    }
}
