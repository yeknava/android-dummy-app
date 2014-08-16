package com2.example.newboston.newboston;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by nava on 8/11/14.
 */
public class GFXSurface extends Activity implements View.OnTouchListener {
    MyGraphicSurfaceClass ourSurfaceView;
    static float x;
    static float y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourSurfaceView = new MyGraphicSurfaceClass(this);
        ourSurfaceView.setOnTouchListener(this);
        x = 0;
        y = 0;
        setContentView(ourSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSurfaceView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ourSurfaceView.resume();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        x = motionEvent.getX();
        y = motionEvent.getY();
        return true;
    }

    public static float getmeX() {
        return x;
    }
    public static float getmeY() {
        return y;
    }
}
