package com2.example.newboston.newboston;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.SurfaceHolder;

/**
 * Created by nava on 8/11/14.
 */
public class GFX extends Activity {
    MyGraphicClass ourView;
    PowerManager.WakeLock wl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //wake-lock
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "whatever");
        super.onCreate(savedInstanceState);
        ourView = new MyGraphicClass(this);
        setContentView(ourView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wl.release();
    }
}
