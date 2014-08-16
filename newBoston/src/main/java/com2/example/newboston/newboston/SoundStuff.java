package com2.example.newboston.newboston;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

/**
 * Created by nava on 8/13/14.
 * play karadan ye seda ba har bar click
 * ya ba negah dashtane touch
 */
public class SoundStuff extends Activity implements View.OnClickListener, View.OnLongClickListener {

    SoundPool soundPool;
    int explosion = 0;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = new View(this);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        setContentView(v);
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        explosion = soundPool.load(this, R.raw.splash_sound, 1);
        mediaPlayer = MediaPlayer.create(this, R.raw.splash_sound);
    }

    @Override
    public void onClick(View view) {
        if (explosion != 0)
        soundPool.play(explosion, 1, 1, 0, 0, 1);
    }

    @Override
    public boolean onLongClick(View view) {
        mediaPlayer.start();
        return false;
    }
}
