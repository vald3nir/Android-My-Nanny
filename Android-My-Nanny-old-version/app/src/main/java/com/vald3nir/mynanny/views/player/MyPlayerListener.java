package com.vald3nir.mynanny.views.player;

import org.videolan.libvlc.MediaPlayer;

import java.lang.ref.WeakReference;

class MyPlayerListener implements MediaPlayer.EventListener {

    private final WeakReference<PlayerFragment> mOwner;

    MyPlayerListener(PlayerFragment owner) {
        mOwner = new WeakReference<>(owner);
    }

    @Override
    public void onEvent(MediaPlayer.Event event) {

        PlayerFragment player = mOwner.get();

        switch (event.type) {
            case MediaPlayer.Event.EndReached:
                player.releasePlayer();
                break;
            case MediaPlayer.Event.Playing:
            case MediaPlayer.Event.Paused:
            case MediaPlayer.Event.Stopped:
            default:
                break;
        }
    }
}