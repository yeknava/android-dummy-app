package com2.example.newboston.newboston;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by nava on 8/11/14.
 */
public class MyGraphicSurfaceClass extends SurfaceView implements Runnable {

    SurfaceHolder ourHolder;
    Thread ourThread = null;
    boolean isRunning = false;

    public MyGraphicSurfaceClass(Context context) {
        super(context);
        ourHolder = getHolder();
        ourThread = new Thread(this);
        ourThread.start();

    }

    @Override
    public void run() {
        while (isRunning) {
            if (!ourHolder.getSurface().isValid()) {
                continue;
            }
            float x = GFXSurface.getmeX();
            float y = GFXSurface.getmeY();
            Canvas canvas = ourHolder.lockCanvas();
            canvas.drawRGB(02, 02, 150);
            if (x != 0 && y !=0) {
                Bitmap circle = BitmapFactory.decodeResource(getResources(), R.drawable.button1);
                canvas.drawBitmap(circle, x-(circle.getWidth()/2), y-(circle.getWidth()/2), null);
            }
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {
        isRunning = false;
        while (true) {
            try {
                ourThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
        ourThread = null;
    }

    public void resume() {
        isRunning = true;
        ourThread = new Thread(this);
        ourThread.start();
    }
}
