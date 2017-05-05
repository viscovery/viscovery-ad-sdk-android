package com.viscovery.player;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnInfoListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.MediaController;

import com.viscovery.consense.ConsenseManager;

public class MainActivity extends AppCompatActivity implements OnInfoListener {
    private static final String API_KEY = "873cbd49-738d-406c-b9bc-e15588567b39";

    private VideoPlayer mPlayer;
    private ConsenseManager mConsenseManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        final String path = getString(R.string.video_url);
        final ViewGroup container = (ViewGroup) findViewById(R.id.container);
        final MediaController controller = new MediaController(this, false);
        controller.setAnchorView(container);
        mPlayer = (VideoPlayer) findViewById(R.id.player);
        mPlayer.setMediaController(controller);
        mPlayer.setOnInfoListener(this);
        mConsenseManager = new ConsenseManager(this, container, mPlayer, API_KEY);
        mConsenseManager.setVideoPath(path);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mPlayer.pause();
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                mConsenseManager.start();
                break;
            default:
                break;
        }
        return false;
    }
}