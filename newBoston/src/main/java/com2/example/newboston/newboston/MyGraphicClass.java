package com2.example.newboston.newboston;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

/**
 * Created by nava on 8/11/14.
 */
public class MyGraphicClass extends View {
    Bitmap gBall;
    float changingY;
    Typeface font;
    public MyGraphicClass(Context context) {
        super(context);
        gBall = BitmapFactory.decodeResource(getResources(), R.drawable.button2);
        changingY = 0;
        font = Typeface.createFromAsset(context.getAssets(), "font.ttf");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        Paint textPaint = new Paint();
        textPaint.setARGB(60, 254, 60, 30);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(60);
        textPaint.setTypeface(font);
        canvas.drawText("MyGraphicClass", canvas.getWidth()/2, 200, textPaint);

        canvas.drawBitmap(gBall, (canvas.getWidth()/2), changingY, null);
        if (changingY < canvas.getHeight()) {
            changingY += 10;
        } else  {
            changingY = 0;
        }
        Rect middleRect = new Rect();
        middleRect.set(0, 400, canvas.getWidth(), 550);
        Paint ourBlue = new Paint();
        ourBlue.setColor(Color.BLUE);
        canvas.drawRect(middleRect, ourBlue);
        invalidate();
    }
}
