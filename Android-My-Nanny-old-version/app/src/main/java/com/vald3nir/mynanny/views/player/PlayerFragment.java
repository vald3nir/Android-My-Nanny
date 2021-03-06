package com.vald3nir.mynanny.views.player;


import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.vald3nir.mynanny.R;
import com.vald3nir.mynanny.views.custom.CustomFragment;

import org.videolan.libvlc.IVLCVout;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import static com.vald3nir.mynanny.utils.AppUtils.getURLStreaming;


public class PlayerFragment extends CustomFragment implements IVLCVout.Callback {

    private SurfaceView mSurface;
    private SurfaceHolder holder;
    private LibVLC libvlc;
    private MediaPlayer mediaPlayer = null;
    private int mVideoWidth;
    private int mVideoHeight;
    private final MediaPlayer.EventListener mPlayerListener = new MyPlayerListener(this);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        mSurface = view.findViewById(R.id.surface);
        holder = mSurface.getHolder();
        return view;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setSize(getActivity(), mVideoWidth);
    }

    @Override
    public void onResume() {
        super.onResume();
        createPlayer(getURLStreaming(getActivity()));
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private void setSize(Activity activity, int width) {
        mVideoWidth = width;
        mVideoHeight = 0;

        if (mVideoWidth * mVideoHeight <= 1 || holder == null || mSurface == null) {
            return;
        }

        int w = activity.getWindow().getDecorView().getWidth();
        int h = activity.getWindow().getDecorView().getHeight();

        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        if (w > h && isPortrait || w < h && !isPortrait) {
            int i = w;
            w = h;
            h = i;
        }

        float videoAR = (float) mVideoWidth / (float) mVideoHeight;
        float screenAR = (float) w / (float) h;

        if (screenAR < videoAR) {
            h = (int) (w / videoAR);
        } else {
            w = (int) (h * videoAR);
        }

        holder.setFixedSize(mVideoWidth, mVideoHeight);

        ViewGroup.LayoutParams layoutParams = mSurface.getLayoutParams();
        layoutParams.width = w;
        layoutParams.height = h;

        mSurface.setLayoutParams(layoutParams);
        mSurface.invalidate();
    }

    private void createPlayer(String media) {
        releasePlayer();
        try {

//            ArrayList<String> options = new ArrayList<>();
//            options.add("--aout=opensles");
//            options.add("--audio-time-stretch");
//            options.add("-vvv");

            libvlc = new LibVLC(); //activity, options

            holder = mSurface.getHolder();
            holder.setKeepScreenOn(true);

            // Creating media player
            mediaPlayer = new MediaPlayer(libvlc);
            mediaPlayer.setEventListener(mPlayerListener);

            // Seting up video output
            final IVLCVout vout = mediaPlayer.getVLCVout();
            vout.setVideoView(mSurface);
            vout.addCallback(this);
            vout.attachViews();
            //vout.setSubtitlesView(mSurfaceSubtitles);
            Media m = new Media(libvlc, Uri.parse(media));
            mediaPlayer.setMedia(m);
            mediaPlayer.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void releasePlayer() {
        if (libvlc == null) {
            return;
        }

        mediaPlayer.stop();

        final IVLCVout vlcVout = mediaPlayer.getVLCVout();
        vlcVout.removeCallback(this);
        vlcVout.detachViews();

        holder = null;
        libvlc.release();
        libvlc = null;

        mVideoWidth = 0;
        mVideoHeight = 0;
    }


    @Override
    public void onNewLayout(IVLCVout vout, int width, int height, int visibleWidth, int visibleHeight, int sarNum, int sarDen) {
        if (width * height == 0) {
            return;
        }
        // store video size
        mVideoWidth = width;
        mVideoHeight = height;
        setSize(getActivity(), mVideoWidth);
    }

    @Override
    public void onSurfacesCreated(IVLCVout ivlcVout) {

    }

    @Override
    public void onSurfacesDestroyed(IVLCVout ivlcVout) {

    }

//    @Override
//    public void onHardwareAccelerationError(IVLCVout vlcVout) {
//        this.releasePlayer();
//    }

}
