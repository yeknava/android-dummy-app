package com2.example.newboston.newboston;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by nava on 8/4/14.
 */
public class Splash extends Activity {
    MediaPlayer splashSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        splashSong = MediaPlayer.create(Splash.this, R.raw.splash_sound);
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());//mire kolle tanzimato migire
        boolean music = getPrefs.getBoolean("checkbox", true);//migim az bakhshe tanzimat kilide "checkbox" ro begir va pishfarz bezar ru true
        if(music) {
            splashSong.start();
        }
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent openStartingPoint = new Intent("com2.example.newboston.newboston.MENU");
                    startActivity(openStartingPoint);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        splashSong.release();
        finish();
    }
}
